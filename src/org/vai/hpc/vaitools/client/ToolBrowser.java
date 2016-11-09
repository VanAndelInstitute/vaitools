package org.vai.hpc.vaitools.client;

import java.util.ArrayList;
import org.vai.hpc.vaitools.client.data.Tool;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class ToolBrowser extends Composite
{

	private static ToolBrowserUiBinder uiBinder = GWT.create(ToolBrowserUiBinder.class);
	private final ToolServiceAsync toolsvc = GWT.create(ToolService.class);
	interface ToolBrowserUiBinder extends UiBinder<Widget, ToolBrowser> 	{}

	@UiField FramedPanel panel;
    @UiField VerticalLayoutContainer vlc;
    @UiField VerticalLayoutContainer topLevel;
    @UiField VerticalLayoutContainer resultsVlc;
	@UiField TextButton searchButton;
	@UiField TextField searchText;
	@UiField Label allResults;
	@UiField Label genomicsResults;
	@UiField Label analysisResults;
    @UiField FlowPanel resultsFlow;
	ArrayList<Tool> tools;
	
	public ToolBrowser()
	{
		initWidget(uiBinder.createAndBindUi(this));
		topLevel.setWidth(com.google.gwt.user.client.Window.getClientWidth()-100);
		topLevel.setHeight(com.google.gwt.user.client.Window.getClientHeight()-80);
		vlc.setScrollMode(ScrollMode.AUTO);
		resultsVlc.setScrollMode(ScrollMode.AUTO);
		com.google.gwt.user.client.Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event)
			{
				topLevel.setWidth(event.getWidth()-100);
				topLevel.setHeight(event.getHeight()-200);
			}});
		
		searchText.addKeyDownHandler(new KeyDownHandler(){
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
					find(null);
			}
		});
		
		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event)
			{
				if(tools == null || tools.size() < 1)
					return;
				String history = event.getValue();
				resultsFlow.clear();
				if(history.contentEquals("results") || history.length()==0)
					showSearchResults();
				else
					resultsFlow.add(new ModelView(tools.get(Integer.parseInt(history)),false));
			}
	    });
		doSearch("");
	}
	@UiHandler("searchButton")
	public void find(SelectEvent event)
	{
		doSearch(searchText.getText());
	}
	
	@UiHandler("allResults")
	public void findAll(ClickEvent event)
	{
		doSearch("");
	}
	
	@UiHandler("genomicsResults")
	public void findone(ClickEvent event)
	{
		doSearch("genomics");
	}
	
	@UiHandler("analysisResults")
	public void findtwo(ClickEvent event)
	{
		doSearch("analysis");
	}
	
	@UiHandler("dataResults")
	public void findthree(ClickEvent event)
	{
		doSearch("data");
	}
	
	private void doSearch(String s)
	{
		toolsvc.getModel(s, new AsyncCallback<ArrayList<Tool>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error",caught.getMessage());
			}

			@Override
			public void onSuccess(final ArrayList<Tool> result) {
				History.newItem("results");
				tools = result;
				showSearchResults();
			}});
	}
	
	private void showSearchResults()
	{
		resultsFlow.clear();
		for(int i = 0; i<tools.size();i++)
		{
			ModelView t = new  ModelView(tools.get(i),true);
			final int j = i;
			t.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("" + j);
				}});
			resultsFlow.add(t);
		}
	}
	
}
