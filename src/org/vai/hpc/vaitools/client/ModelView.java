package org.vai.hpc.vaitools.client;
import org.vai.hpc.vaitools.client.data.Tool;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;


public class ModelView extends Composite implements HasClickHandlers
{

	private static ModelViewUiBinder uiBinder = GWT.create(ModelViewUiBinder.class);

	interface ModelViewUiBinder extends UiBinder<Widget, ModelView>	{}
	@UiField FramedPanel panel;
    @UiField VerticalLayoutContainer vlc;
    @UiField VerticalLayoutContainer topToolPanel;
	//private final ToolServiceAsync toolsvc = GWT.create(ToolService.class);
	Tool tool;
	Label clickableHeading;
	
	public ModelView(final Tool tool, Boolean isSummary) 
	
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.tool = tool;
		//panel.setCollapsible(true);
		panel.setBorders(true);
		panel.setHeading(tool.getSection("Name"));
		if(isSummary)
		{
			clickableHeading = new Label(tool.getSection("Name"));
			clickableHeading.setStylePrimaryName("toolHeadingLink");
			vlc.add(clickableHeading);
			HTML content = new HTML(tool.getSection("Overview"));
			content.setStylePrimaryName("toolContent");
			vlc.add(content);
		}
		else
		{
			topToolPanel.setStylePrimaryName("toolPanelBig");
			panel.removeStyleName("toolPanelSmallFramedPanel");
			for(int i = 0; i< tool.getAllSections().size(); i++)
			{
				
				Label heading = new Label(tool.getAllSections().get(i));
				heading.setStylePrimaryName("toolHeading");
				vlc.add(heading);
				HTML content = new HTML(tool.getSection(tool.getAllSections().get(i)));
				content.setStylePrimaryName("toolContent");
				vlc.add(content);
				if(i<tool.getAllSections().size()-1)
					vlc.add(new HTML("<hr  class=\"toolhr\" />"));
			}
		}
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return clickableHeading.addClickHandler(handler);
	}
}