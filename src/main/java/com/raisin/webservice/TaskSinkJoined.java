package com.raisin.webservice;

public class TaskSinkJoined{
	
	 private String url = "http://localhost:7299/sink/a";
	 
    public void run() {
		
		try {
			Helper.postRequestJoined(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
