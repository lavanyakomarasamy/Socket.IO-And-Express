package com.avaya.threading;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestThreadExecuter implements Runnable {

	private final int statusCode;
	   private final String message;
	   public RestThreadExecuter(int statusCode, String message) { 
		   this.statusCode=statusCode;
		   this.message=message;
	   
	   }
	@Override
	public void run() {
		HttpURLConnection con = null;
		try {
			URL url = new URL("http://localhost:4000/activateResponseHandler");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.connect();
			int status = con.getResponseCode();
			System.out.println("activateResponseHandler:"+status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			con.disconnect();;
		}
	}

}
