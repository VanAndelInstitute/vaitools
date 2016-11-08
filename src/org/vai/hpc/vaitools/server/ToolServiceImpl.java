package org.vai.hpc.vaitools.server;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.vai.hpc.vaitools.client.ToolService;
import org.vai.hpc.vaitools.client.data.Tool;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ToolServiceImpl extends RemoteServiceServlet implements ToolService
{
	@Override
	public ArrayList<Tool> getModel(String search)
	{
		ArrayList<Tool> tools = new ArrayList<Tool>();
		try
		{
			File folder = new File("tools");
			Charset charset = Charset.forName("ISO-8859-1");
			for (final File fileEntry : folder.listFiles()) 
			{
				Tool t = new Tool();
				System.out.println(fileEntry.getName());
				List<String> lines = Files.readAllLines(Paths.get(fileEntry.getPath()), charset);
				Boolean newEntry=true;
				String header = "";
				String content = "";
				for (String line : lines) 
				{
					if(newEntry && line.length() > 1)
					{
						header = new String(line);
						content = "";
						newEntry=false;
					}
					else if(line.length()>1)
					{
						content += line;
					}
					else if(content.length() > 1 && header.length() > 1)
					{
						t.getTextSections().put(header, content);
						newEntry=true;
						header="";
						content="";
					}
			    }
				tools.add(t);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return tools;
	}
}
