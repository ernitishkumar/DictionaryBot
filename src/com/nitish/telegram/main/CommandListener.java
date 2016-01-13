package com.nitish.telegram.main;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nitish.telegram.drivers.BotCommunicator;
import com.nitish.telegram.response.DictionaryResponse;

public class CommandListener {
	private BotCommunicator botCommunicator=new BotCommunicator();
	public void listen() throws IOException,JSONException{
		System.out.println("DictionaryBot is listening now...");
		int last_update_id = 0; // last processe	d command
		String response="";
		JSONObject jsonObject=null;
		JSONArray resulArray=null;
		while (true) {
			response = botCommunicator.getUpdates(last_update_id++);
			jsonObject=new JSONObject(response.toString());
			resulArray=jsonObject.getJSONArray("result");
			if (resulArray.isNull(0)){ 
				continue;
			}else{
				last_update_id = resulArray.getJSONObject(resulArray.length() - 1).getInt("update_id") + 1;
			}
			for (int i = 0; i < resulArray.length(); i++) {
				// process commands
				JSONObject message = resulArray.getJSONObject(i).getJSONObject("message");
				int chat_id = message.getJSONObject("chat").getInt("id");
				String username = message.getJSONObject("chat").getString("username");
				if(username==null)username="User";
				String text = message.getString("text");
				System.out.println("Message recieved as : "+text);
				if (text.contains("/start")) {
					String reply = "Hi "+username+", I am a Dictionary bot.\n" +
							"You can get meanings of word by just sending any word to me.\n" +
							"Your chat_id is " + chat_id + "\n" ;
					botCommunicator.sendMessage(chat_id, reply);
				}else{
					botCommunicator.sendMessage(chat_id,prepareMeaning(text));
				}
			}

		}
	}

	private String prepareMeaning(String text)throws IOException,JSONException {
		ArrayList<DictionaryResponse> meanings=botCommunicator.getMeaning(text.trim());
		String reply="";
		if(meanings.size()==0){
		reply="Unable to find meaning for : "+text.toUpperCase()+"\nPlease try a different word";
		}else if(meanings.size()>0){
			DictionaryResponse meaning=meanings.get(0);
			reply="Meaning of "+text.toUpperCase()+" : \n \n";
			int i=1;
			for(String m:meaning.getDefinitions()){
				reply+=(i++)+". "+m+" \n";
				reply+="\n";
			}
		}
		return reply;
	}
}
