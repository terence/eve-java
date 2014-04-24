/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.agent;

import java.net.URI;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.junit.Test;

import com.almende.eve.capabilities.handler.SimpleHandler;
import com.almende.eve.scheduling.Scheduler;
import com.almende.eve.scheduling.SchedulerFactory;
import com.almende.eve.transport.Receiver;
import com.almende.util.jackson.JOM;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * The Class TestScheduling.
 */
public class TestScheduling extends TestCase {
	private static final Logger	LOG	= Logger.getLogger(TestScheduling.class
											.getName());
	
	/**
	 * Test scheduling.
	 */
	@Test
	public void testScheduling() {
		ObjectNode params = JOM.createObjectNode();
		ObjectNode state = JOM.createObjectNode();
		state.put("class", "com.almende.eve.state.file.FileStateService");
		state.put("json", true);
		state.put("path", ".eveagents_schedulingtest");
		state.put("id", "testScheduling");
		params.put("state", state);
		params.put("senderUrl", "local:scheduler");
		params.put("class", "com.almende.eve.scheduling.PersistentSchedulerService");
		
		Scheduler test = SchedulerFactory.getScheduler(params,
				new SimpleHandler<Receiver>(new MyReceiver()));
		
		test.schedule("Hi there!", DateTime.now());
		
		test.schedule("Hi there!", DateTime.now().plusSeconds(10));
		
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {}
		
	}
	
	class MyReceiver implements Receiver {
		@Override
		public void receive(Object msg, URI senderUrl, String tag) {
			LOG.warning("Received msg:'" + msg + "' from: "
					+ senderUrl.toASCIIString());
		}
		
	}
}
