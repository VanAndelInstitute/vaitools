package org.vai.hpc.vaitools.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;
import org.vai.hpc.vaitools.client.data.Tool;
/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface ToolService extends RemoteService
{
	ArrayList<Tool> getModel(String search);
}
