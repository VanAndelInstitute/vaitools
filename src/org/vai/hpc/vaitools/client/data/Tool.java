package org.vai.hpc.vaitools.client.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Tool implements IsSerializable
{
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
	
	public String toString()
	{
		String ret = "";
		for(String k : getAllSections())
			ret += k + " " + getSection(k) + "\n";
		
		return ret;
	}
}
