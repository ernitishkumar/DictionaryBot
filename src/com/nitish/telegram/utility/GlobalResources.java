package com.nitish.telegram.utility;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

public class GlobalResources {
	public static HttpClientBuilder proxyClientBuilder(){
		HttpClientBuilder hcBuilder = HttpClients.custom();
		HttpHost proxy = new HttpHost("10.98.0.26", 5000, "http");
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		hcBuilder.setRoutePlanner(routePlanner);
		return hcBuilder;
	}
}
