package entity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiConnect {

	private static HttpURLConnection connection;
	
	
	public JSONObject getJsonObj(String urlApi, String params) {

		try {
			URL url = new URL(urlApi + params);
			connection = (HttpURLConnection) url.openConnection();
		
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			
			if(status != 200) {
				throw new RuntimeException("HttpResponseCode: " + status);			
			} else {
				
				StringBuilder ApiContent = new StringBuilder(); 
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					ApiContent.append(scanner.nextLine());
				}
				
				scanner.close();
				
				JSONParser parse = new JSONParser();
				
				return (JSONObject) parse.parse(String.valueOf(ApiContent));
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		
		return null;
	}
	
	public JSONArray getJsonArray(String urlApi, String params) {

		try {
			URL url = new URL(urlApi + params);
			connection = (HttpURLConnection) url.openConnection();
		
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			
			if(status != 200) {
				throw new RuntimeException("HttpResponseCode: " + status);			
			} else {
				
				StringBuilder ApiContent = new StringBuilder(); 
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					ApiContent.append(scanner.nextLine());
				}
				
				scanner.close();
				
				JSONParser parse = new JSONParser();
				
				return (JSONArray) parse.parse(String.valueOf(ApiContent));
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		
		return null;
	}
}
