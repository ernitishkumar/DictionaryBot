package com.nitish.telegram.main;

import java.io.IOException;

import org.json.JSONException;

public class DictionaryBotServer {

	public static void main(String[] args) {
		System.out.println("Starting Dictionary Bot server : ");
		CommandListener commandListener=new CommandListener();
		try {
			commandListener.listen();
		} catch (IOException e) {
			System.out.println("IOException in command listener : "+e);
		} catch (JSONException e) {
			System.out.println("JSONException in command listener : "+e);
		}
	}
}
