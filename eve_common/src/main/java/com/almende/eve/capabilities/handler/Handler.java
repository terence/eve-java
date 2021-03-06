/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.capabilities.handler;


/**
 * The Interface Handler.
 * 
 * @param <T>
 *            the generic type
 */
public interface Handler<T> {

	/**
	 * Gets the wrapped handler object, if not found: triggers wake process and
	 * waits for signal from update().
	 * 
	 * @return the t
	 */
	T get();

	/**
	 * Gets the wrapped handler object, returns null if not found.
	 *
	 * @return the no wait
	 */
	T getNoWait();

	/**
	 * Update the handler with new data, signals the waiting get() to proceed.
	 * 
	 * @param newHandler
	 *            the new handler
	 */
	void update(Handler<T> newHandler);

	/**
	 * Gets the key of this handle, when relevant. Returns null if no such key
	 * is available.
	 * 
	 * @return the key
	 */
	String getKey();

}
