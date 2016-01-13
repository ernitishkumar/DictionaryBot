package com.nitish.telegram.utility;

public class BotConfig {
	
	private static final String telegramServerURL="https://api.telegram.org/bot";
	
	private static final String botToken="146096923:AAGFy8ZL9mFqHuMvWzE45WFFNqeMhexeKaA";
	
	public static String getTelegramServerURL() {
		return telegramServerURL;
	}

	public static String getBotToken() {
		return botToken;
	}
	
	public static String getCompleteURL(){
		return getTelegramServerURL()+getBotToken();
	}
}
