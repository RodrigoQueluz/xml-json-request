package com.raisin.webservice;

public class TaskSinkOrphaned{
	
	 private String url = "http://localhost:7299/sink/a";
	 
    public void run() {
		
		try {
			Helper.postRequestOrphaned(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
