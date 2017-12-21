package com.raisin.webservice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.json.JSONObject;

public class Helper {

	private static final String USER_AGENT = "";

	private static StringBuilder getResult(String url) throws IOException {

		StringBuilder result = new StringBuilder();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			InputStream in = new BufferedInputStream(con.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}

		} else if(responseCode == 406){
			result.append("");
		} 

		return result;
	}
	
	public static void postRequestJoined(String urlString) throws Exception{
		
        Set<String> joined = RecordsSingleton.getInstance().getJoineds();
        for (String id : joined) {

        	if(!RecordsSingleton.getInstance().getJoinedsSent().contains(id)){
        		
        		JSONObject object = new JSONObject();
        		object.put("kind", "joined");
        		object.put("id", id);
        		
        		URL url = new URL(urlString);
        		
        		HttpURLConnection con = (HttpURLConnection) url.openConnection();
        		con.setDoOutput(true);
        		con.setDoInput(true);
        		con.setRequestProperty("Content-Type", "application/json");
        		con.setRequestProperty("Accept", "application/json");
        		con.setRequestMethod("POST");
        		
        		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        		wr.write(object.toString());
        		wr.flush();
        		
        		StringBuilder sb = new StringBuilder();  
        		int HttpResult = con.getResponseCode(); 
        		if (HttpResult == HttpURLConnection.HTTP_OK) {
        			BufferedReader br = new BufferedReader(
        					new InputStreamReader(con.getInputStream(), "utf-8"));
        			String line = null;  
        			while ((line = br.readLine()) != null) {  
        				sb.append(line + "\n");  
        			}
        			br.close();
        			
        			RecordsSingleton.getInstance().getJoinedsSent().add(id);
        		} 
        	}
        	
        }
        
	}

	public static StringBuilder requestJson(String url) throws Exception {

		return getResult(url);

	}

	public static StringBuilder requestXML(String url) throws Exception {
		return getResult(url);

	}

	public static void postRequestOrphaned(String urlString) throws Exception {
		 Set<String> orphaned = RecordsSingleton.getInstance().getOrphaneds();
	        for (String id : orphaned) {
	        	
		        System.out.println(id);

	        	JSONObject object = new JSONObject();
	        	object.put("kind", "orphaned");
	        	object.put("id", id);
	        	
	        	URL url = new URL(urlString);
	        	
	        	HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        	con.setDoOutput(true);
	        	con.setDoInput(true);
	        	con.setRequestProperty("Content-Type", "application/json");
	        	con.setRequestProperty("Accept", "application/json");
	        	con.setRequestMethod("POST");
	            
	            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
	            wr.write(object.toString());
	            wr.flush();
	            
	            StringBuilder sb = new StringBuilder();  
	            int HttpResult = con.getResponseCode(); 
	            System.out.println(HttpResult);
	            if (HttpResult == HttpURLConnection.HTTP_OK) {
	                BufferedReader br = new BufferedReader(
	                        new InputStreamReader(con.getInputStream(), "utf-8"));
	                String line = null;  
	                while ((line = br.readLine()) != null) {  
	                    sb.append(line + "\n");  
	                }
	                br.close();
	            }
	            
	        }
		
	}

}
