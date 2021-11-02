package entity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiConnect {

	private final String urlApi;
	private static HttpURLConnection connection;
	
	public ApiConnect(String urlApi) {
		//this.urlApi = urlApi;
		this.urlApi = "https://www.mercadobitcoin.net/api/btc/ticker";
	}
	
	public JSONObject getJsonObj() {

		try {
			URL url = new URL(urlApi);
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
				
				System.out.println(ApiContent);	
				
				return (JSONObject) parse.parse(String.valueOf(ApiContent));
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
		
		return null;
	}
}
