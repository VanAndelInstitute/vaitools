package org.vai.hpc.vaitools.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vaitools implements EntryPoint
{
	
	public void onModuleLoad() 
	{
		Viewport viewport = new Viewport();
		ToolBrowser s = new ToolBrowser();
		viewport.setWidget(s);
		RootLayoutPanel.get().add(viewport);
	}
}
