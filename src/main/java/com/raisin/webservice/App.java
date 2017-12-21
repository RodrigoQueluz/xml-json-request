package com.raisin.webservice;

public class App 
{
	static Helper helper = new Helper();
	
    public static void main( String[] args )
    {
    	
    		Thread threadSourceA = new Thread(new TaskSourceA());
    	    Thread threadSourceB = new Thread(new TaskSourceB());
    	    
    	    threadSourceA.start();
    	    threadSourceB.start();
    		
    	    while(threadSourceA.isAlive() && threadSourceB.isAlive()){
    	    	//wait untill threads ends
    	    }
    	    
    	    threadSourceA.interrupt();
    	    threadSourceB.interrupt();

    	    TaskSinkJoined joined = new TaskSinkJoined();
			joined.run();
    	    
    		TaskSinkOrphaned taskOrphaned = new TaskSinkOrphaned();
    		taskOrphaned.run();

    }
    
   
}
