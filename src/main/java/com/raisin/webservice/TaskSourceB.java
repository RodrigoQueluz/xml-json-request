package com.raisin.webservice;

import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;

public class TaskSourceB implements Runnable{
	
	 private String url = "http://localhost:7299/source/b";
	private String lastStatus = "";
	 
	    public void run() {
			while(!"done".equals(lastStatus )){
				try {
					StringBuilder response = Helper.requestXML(url);
					
					if(response.toString() != ""){
						
						SAXBuilder saxBuilder = new SAXBuilder();
						Document doc = saxBuilder.build(new StringReader(response.toString()));
						
						if(doc.getRootElement().getChild("id") != null){
							String id = doc.getRootElement().getChild("id").getAttribute("value").getValue();
							RecordsSingleton.getInstance().processId(id);
							
						} else if(doc.getRootElement().getChild("done") != null){
							lastStatus = "done";
						}
					}
					
				} catch(JDOMParseException e){
					//ignore
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					lastStatus = "done";
				}
			}
	        
	    }

}
