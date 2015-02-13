/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.algorithms.test;

import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.Test;

import com.almende.eve.algorithms.DAAValueBean;

/**
 * The Class TestValueBean.
 */
public class TestDAA extends TestCase {
	private static final Logger	LOG	= Logger.getLogger(TestDAA.class
											.getName());

	/**
	 * Test keys.
	 */
	@Test
	public void testValueBean() {

		final int width = 1000;
		final double value = 125.0;
		DAAValueBean bean = new DAAValueBean(width, 10);
		bean.generate(value, 10);

		assertTrue(Math.abs(value - bean.computeSum()) < 0.1);
		assertEquals(new Integer(10), bean.getTtlArray()[15]);

		DAAValueBean bean2 = new DAAValueBean(width, 10);
		bean2.generate(value, 10);
		bean2.minimum(bean);

		double sum = Math.abs(bean2.computeSum() - 2 * bean.computeSum());
		LOG.warning("bean1:" + bean.computeSum() + " bean2:"
				+ bean2.computeSum() + " sum:" + sum + " ("
				+ (sum * 100.0 / bean2.computeSum()) + "%)");
	}
	
	/**
	 * Test agents.
	 */
	@Test
	public void testAgents(){
		//Setup 5 agents, each with single "1" value in DAA,
		//use Trickle to share this information
		//Change the value of a single agent to 3
		//Run a few seconds, check sum at other agent
	}
}