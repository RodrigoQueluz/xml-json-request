package com.raisin.webservice;

import java.util.HashSet;
import java.util.Set;

public class RecordsSingleton {

	private Set<String> joined = new HashSet<String>();
	private Set<String> joinedSent = new HashSet<String>();

	private Set<String> orphaned = new HashSet<String>();

	private static RecordsSingleton instance;

	private RecordsSingleton(){}
	
	public static RecordsSingleton getInstance(){
	    if(instance == null){
	        synchronized (RecordsSingleton.class) {
	            if(instance == null){
	                instance = new RecordsSingleton();
	            }
	        }
	    }
	    return instance;
	}
	
	public Set<String> getJoineds(){
		return this.joined;
	}
	
	public Set<String> getJoinedsSent(){
		return this.joinedSent;
	}
	
	public Set<String> getOrphaneds(){
		return this.orphaned;
	}
		
	public void processId(String id){
		if(!this.orphaned.contains(id)){
			this.orphaned.add(id);
		} else {
			this.orphaned.remove(id);
			this.joined.add(id);
		}
	}
}
