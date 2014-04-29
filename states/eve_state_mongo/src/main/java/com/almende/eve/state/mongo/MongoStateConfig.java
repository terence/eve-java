/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.state.mongo;

import com.almende.eve.state.StateConfig;
import com.almende.util.jackson.JOM;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * The Class MongoStateConfig.
 */
public class MongoStateConfig extends StateConfig {
	
	/**
	 * Instantiates a new memory state config.
	 */
	public MongoStateConfig() {
		this(JOM.createObjectNode());
	}
	
	/**
	 * Instantiates a new memory state config.
	 * 
	 * @param node
	 *            the node
	 */
	public MongoStateConfig(final ObjectNode node) {
		super(node);
		if (!node.has("class")) {
			this.put("class", MongoStateService.class.getName());
		}
	}
	
	/**
	 * Sets the url.
	 * 
	 * @param host
	 *            the new host
	 */
	public void setHost(String host) {
		this.put("host", host);
	}
	
	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getHost() {
		if (this.has("host")) {
			return this.get("host").asText();
		}
		return "localhost";
	}
	
	/**
	 * Sets the port.
	 * 
	 * @param port
	 *            the new port
	 */
	public void setPort(int port) {
		this.put("port", port);
	}
	
	/**
	 * Gets the port.
	 * 
	 * @return the port
	 */
	public int getPort() {
		if (this.has("port")) {
			return this.get("port").asInt();
		}
		return 27017;
	}
	
	/**
	 * Sets the database.
	 * 
	 * @param database
	 *            the new database
	 */
	public void setDatabase(String database) {
		this.put("database", database);
	}
	
	/**
	 * Gets the database.
	 * 
	 * @return the database
	 */
	public String getDatabase() {
		if (this.has("database")) {
			return this.get("database").asText();
		}
		return "eve";
	}
	
	/**
	 * Sets the collection.
	 * 
	 * @param collection
	 *            the new collection
	 */
	public void setCollection(String collection) {
		this.put("collection", collection);
	}
	
	/**
	 * Gets the collection.
	 * 
	 * @return the collection
	 */
	public String getCollection() {
		if (this.has("collection")) {
			return this.get("collection").asText();
		}
		return "agents";
	}
}
