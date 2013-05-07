package com.almende.eve.entity;

import com.almende.eve.agent.Agent;
import com.almende.eve.rpc.jsonrpc.JSONRequest;
import com.almende.eve.rpc.jsonrpc.jackson.JOM;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Poll implements ResultMonitorConfigType {
	int interval;
	
	public Poll(int interval){
		this.interval=interval;
	};
	
	public String init(ResultMonitor monitor, Agent agent){
		ObjectNode params = JOM.createObjectNode();
		params.put("monitorId",monitor.id);
		JSONRequest request = new JSONRequest("doPoll",params);
		
		System.err.println("Setting scheduler task");
		return agent.getScheduler().createTask(request, interval, true, false);
	}
}