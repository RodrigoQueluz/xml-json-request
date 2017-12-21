package com.raisin.webservice;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TaskSourceA implements Runnable{
	
	 private String url = "http://localhost:7299/source/a";
	 private String lastStatus = "";
	 
    public void run() {
		while(!"done".equals(lastStatus)){
			try {
				
				StringBuilder response = Helper.requestJson(url);
				
				if(response.toString() != ""){
					
					JsonElement root = new JsonParser().parse(response.toString());
					
					if(root == null){
						lastStatus = "done";
					} else {
						
						JsonObject result = root.getAsJsonObject();
						lastStatus = result.get("status").getAsString();
						
						if("ok".equals(lastStatus)){
							String id = result.get("id").getAsString();
							RecordsSingleton.getInstance().processId(id);
							
							
						} 
					}
					TaskSinkJoined joined = new TaskSinkJoined();
					joined.run();
					
				} else {
				}
			} catch (JsonSyntaxException e){
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				lastStatus = "done";
			} 
		}
        
    }

}
