package org.vai.hpc.vaitools.client;
import org.vai.hpc.vaitools.client.data.Tool;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;


public class ModelView extends Composite 
{

	private static ModelViewUiBinder uiBinder = GWT.create(ModelViewUiBinder.class);

	interface ModelViewUiBinder extends UiBinder<Widget, ModelView>	{}
	@UiField FramedPanel panel;
    @UiField VerticalLayoutContainer vlc;

	private final ToolServiceAsync toolsvc = GWT.create(ToolService.class);
	Tool tool;
	
	public ModelView(final Tool tool)
	
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.tool = tool;
		panel.setCollapsible(true);
		panel.setBorders(false);
		panel.setHeading(tool.getSection("Name"));
		for(String section : tool.getAllSections())
		{
			Label heading = new Label(section);
			heading.setStylePrimaryName("toolHeading");
			vlc.add(heading);
			HTML content = new HTML(tool.getSection(section));
			content.setStylePrimaryName("toolContent");
			vlc.add(content);
		}
	}
}