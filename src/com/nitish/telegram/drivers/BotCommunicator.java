package com.nitish.telegram.drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nitish.telegram.utility.GlobalResources;
import com.nitish.telegram.response.DictionaryResponse;
import com.google.gson.Gson;
import com.nitish.telegram.utility.BotConfig;

public class BotCommunicator {

	public String sendMessage(int chatId,String message) throws IOException {
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpClient httpClient =  GlobalResources.proxyClientBuilder().build();
		String sendMessageURL=BotConfig.getCompleteURL()+"/sendMessage";
		HttpPost httpPost = new HttpPost(sendMessageURL);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("chat_id", String.valueOf(chatId)));
		urlParameters.add(new BasicNameValuePair("text",message));
		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPost.setEntity(postParams);
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		httpClient.close();
		return response.toString();
	}

	public String getUpdates(int offset) throws IOException {
		CloseableHttpClient httpClient =  GlobalResources.proxyClientBuilder().build();
		String sendMessageURL=BotConfig.getCompleteURL()+"/getUpdates";
		HttpPost httpPost = new HttpPost(sendMessageURL);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("offset",String.valueOf(offset)));
		HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
		httpPost.setEntity(postParams);
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		//System.out.println("POST Response Status:: "+ httpResponse.getStatusLine().getStatusCode());
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		// print result
		//System.out.println(response.toString());
		httpClient.close();
		return response.toString();
	}
	
	public ArrayList<DictionaryResponse> getMeaning(String word) throws IOException,JSONException {
		CloseableHttpClient httpClient =  GlobalResources.proxyClientBuilder().build();
		String sendMessageURL="http://dictionaryapi.net/api/definition/"+word.trim();
		HttpGet httpGet = new HttpGet(sendMessageURL);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		// print result
		//System.out.println(response.toString());
		httpClient.close();
		JSONArray recievedResponse=new JSONArray(response.toString().trim());
		ArrayList<DictionaryResponse> meanings=new ArrayList<DictionaryResponse>();
		for(int i=0;i<recievedResponse.length();i++){
			JSONObject meaning=recievedResponse.getJSONObject(i);
			if(meaning.getString("PartOfSpeech").trim().toLowerCase().equals("noun")){
				DictionaryResponse dictionaryResponse = new Gson().fromJson(meaning.toString(),DictionaryResponse.class);
				meanings.add(dictionaryResponse);
				break;
			}
		}
		return meanings;
	}
	
}
