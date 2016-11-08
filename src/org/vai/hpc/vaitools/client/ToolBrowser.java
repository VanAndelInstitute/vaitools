package org.vai.hpc.vaitools.client;

import java.util.ArrayList;

import org.vai.hpc.vaitools.client.data.Tool;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
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
    @UiField VerticalLayoutContainer resultsVlc;
	@UiField TextButton searchButton;
	@UiField TextField searchText;
	ArrayList<Widget> historyWidgets = new ArrayList<Widget>();
	ArrayList<Tool> tools;
	
	
	
	public ToolBrowser()
	{
		initWidget(uiBinder.createAndBindUi(this));
		panel.setWidth(com.google.gwt.user.client.Window.getClientWidth()-100);
		panel.setHeight(com.google.gwt.user.client.Window.getClientHeight()-80);
		vlc.setScrollMode(ScrollMode.AUTO);
		resultsVlc.setScrollMode(ScrollMode.AUTO);
		com.google.gwt.user.client.Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event)
			{
				panel.setWidth(event.getWidth()-100);
				panel.setHeight(event.getHeight()-200);
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
				resultsVlc.clear();
				for(Widget w : historyWidgets)
					resultsVlc.add(w);
			}
		      
		      
		    });
	}
	@UiHandler("searchButton")
	public void find(SelectEvent event)
	{
		toolsvc.getModel(searchText.getText(), new AsyncCallback<ArrayList<Tool>>(){

			@Override
			public void onFailure(Throwable caught) {
				Info.display("Error",caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<Tool> result) {
				resultsVlc.clear();
				Label hits = new Label(result.size() + " tools matched");
				hits.setStylePrimaryName("hitsLabel");
				resultsVlc.add(hits);
				for(final Tool m : result)
					resultsVlc.add(new ModelView(m));
			}});

	}
}
