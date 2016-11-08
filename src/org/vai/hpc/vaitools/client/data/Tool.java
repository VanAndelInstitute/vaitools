package org.vai.hpc.vaitools.client.data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Tool implements Serializable
{
	private static final long serialVersionUID = 1L;
	String id;
	LinkedHashMap<String,String> sectionMap = new LinkedHashMap<String,String>();
	public LinkedHashMap<String, String> getTextSections() {
		return sectionMap;
	}
	public void setTextSections(LinkedHashMap<String, String> textSections) {
		this.sectionMap = textSections;
	}
	public String getSection(String sectionName)
	{
		if(this.sectionMap.containsKey(sectionName))
			return this.sectionMap.get(sectionName);
		else
			return "";
	}
	public List<String> getAllSections()
	{
		return new ArrayList<String>(this.getTextSections().keySet());
	}
}
