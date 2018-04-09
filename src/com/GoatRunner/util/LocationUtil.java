package com.GoatRunner.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

//Class which uses the Google API to calculate distance and estimated time
public class LocationUtil {

	private static final String ApiKey = "AIzaSyB8uV6rMp32emV_GmUbcH-qUVZgMhGeSjI";

	public static Integer getDistance(double lat1, double long1, double lat2, double long2) {
		// Client c = new Client();

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://maps.googleapis.com/maps/api/distancematrix/json?")
				.queryParam("units", "imperial").queryParam("origins", lat1 + "," + long1)
				.queryParam("destinations", lat2 + "," + long2);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		String responseString = response.readEntity(String.class);
		JSONObject jsonObject;
		//HashMap<Object, Object> timeValueMap = new HashMap<>();
		String estimatedTime = null;
		int actualDistance = 0;
		// response.readEntity(String.class);
		try {
			jsonObject = new JSONObject(responseString);
			//JSONArray array =
					JSONObject jsonObject2 = (JSONObject) jsonObject.getJSONArray("rows").get(0);
					JSONObject timeObject = (JSONObject) jsonObject2.getJSONArray("elements").get(0);
					//JSONObject timeDuration = timeObject.getJSONObject("duration");
					JSONObject distance = timeObject.getJSONObject("distance");
					//timeValueMap.put(timeDuration.get("text"), timeDuration.get("value"));
					//estimatedTime = (String) timeDuration.get("text");
					actualDistance = distance.getInt("value");
					//System.out.println(timeDuration.get("text"));
			//array.get(0);

		} catch (JSONException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualDistance;

	}

	public static String getTime(double lat1, double long1, double lat2, double long2) {
		// Client c = new Client();

		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target("https://maps.googleapis.com/maps/api/distancematrix/json?")
				.queryParam("units", "imperial").queryParam("origins", lat1 + "," + long1)
				.queryParam("destinations", lat2 + "," + long2);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		String responseString = response.readEntity(String.class);
		JSONObject jsonObject;
		//HashMap<Object, Object> timeValueMap = new HashMap<>();
		String estimatedTime = null;
		int actualDistance = 0;
		// response.readEntity(String.class);
		try {
			jsonObject = new JSONObject(responseString);
			//JSONArray array =
					JSONObject jsonObject2 = (JSONObject) jsonObject.getJSONArray("rows").get(0);
					JSONObject timeObject = (JSONObject) jsonObject2.getJSONArray("elements").get(0);
					JSONObject timeDuration = timeObject.getJSONObject("duration");
					JSONObject distance = timeObject.getJSONObject("distance");
					//timeValueMap.put(timeDuration.get("text"), timeDuration.get("value"));
					estimatedTime = (String) timeDuration.get("text");
					actualDistance = distance.getInt("value");
					//System.out.println(timeDuration.get("text"));
			//array.get(0);

		} catch (JSONException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estimatedTime;

	}

}
