package org.vai.hpc.vaitools.client;


import java.util.ArrayList;

import org.vai.hpc.vaitools.client.data.Tool;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ToolServiceAsync
{
		void getModel(String search, AsyncCallback<ArrayList<Tool>> callback);
}
