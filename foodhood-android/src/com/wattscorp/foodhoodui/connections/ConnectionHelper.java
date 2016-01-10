package com.wattscorp.foodhoodui.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

public class ConnectionHelper {
	public static final String IP_PORT_CONTEXT = "http://192.168.0.107:8080/foodhood/rest";
	
	public static String sendDataToServer(String jsonDataString, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		StringEntity se;
		String resultSt = null;
		try {
			se = new StringEntity(jsonDataString);
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpost.setEntity(se);
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			resultSt = convertStreamToString(inputStream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultSt;
	}

	public static String sendDataToServer(List<NameValuePair> params, String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		String resultSt = "Error occured";
		try {
			httpost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			resultSt = convertStreamToString(inputStream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultSt;
	}
	
	
	public static String sendDataToServerWithNoParams(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		String resultSt = null;
		try {
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			resultSt = convertStreamToString(inputStream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultSt;
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
