/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.scheduling;

import java.util.logging.Logger;

import com.almende.eve.capabilities.handler.Handler;
import com.almende.eve.protocol.jsonrpc.formats.Caller;
import com.almende.util.uuid.UUID;

/**
 * The Class PersistentSchedulerService.
 */
public class SyncSchedulerBuilder extends SimpleSchedulerBuilder {
	private static final Logger	LOG	= Logger.getLogger(SyncSchedulerBuilder.class
											.getName());

	/*
	 * (non-Javadoc)
	 * @see
	 * com.almende.eve.capabilities.CapabilityService#get(com.fasterxml.jackson
	 * .databind.node.ObjectNode, com.almende.eve.capabilities.handler.Handler,
	 * java.lang.Class)
	 */
	@Override
	public SyncScheduler build() {
		final SyncSchedulerConfig config = SyncSchedulerConfig
				.decorate(getParams());
		String id = config.getId();
		if (id == null) {
			id = new UUID().toString();
			LOG.warning("Parameter 'id' is required for SyncScheduler. (giving temporary name: "
					+ id + ")");
		}

		SyncScheduler result = null;
		if (INSTANCES.containsKey(id)) {
			result = (SyncScheduler) INSTANCES.get(id);
			final Handler<Caller> oldHandle = result.getHandle();
			oldHandle.update(TYPEUTIL.inject(getHandle()));
		} else {
			result = new SyncScheduler(config, TYPEUTIL.inject(getHandle()));
		}
		INSTANCES.put(id, result);
		return result;
	}

}
