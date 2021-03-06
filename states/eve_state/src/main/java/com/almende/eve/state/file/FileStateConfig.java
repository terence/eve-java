/*
 * Copyright: Almende B.V. (2014), Rotterdam, The Netherlands
 * License: The Apache Software License, Version 2.0
 */
package com.almende.eve.state.file;

import java.util.logging.Logger;

import com.almende.eve.state.StateConfig;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * The Class FileStateConfig.
 */
public class FileStateConfig extends StateConfig {
	private static final Logger	LOG		= Logger.getLogger(FileStateConfig.class
												.getSimpleName());
	private static final String	BUILDER	= FileStateBuilder.class.getName();

	protected FileStateConfig() {
		super();
	}

	/**
	 * Instantiates a new file state config.
	 *
	 * @return the file state config
	 */
	public static FileStateConfig create() {
		final FileStateConfig res = new FileStateConfig();
		res.setBuilder(BUILDER);
		return res;
	}

	/**
	 * Instantiates a new file state config.
	 * 
	 * @param node
	 *            the node
	 */
	public static FileStateConfig decorate(final ObjectNode node) {
		final FileStateConfig res = new FileStateConfig();
		res.extend(node);
		return res;
	}

	/**
	 * Sets the json. (Optional, default is true)
	 * 
	 * @param json
	 *            the new json
	 */
	public void setJson(final boolean json) {
		this.put("json", json);
	}

	/**
	 * Gets the json.
	 * 
	 * @return the json
	 */
	public boolean getJson() {
		if (this.has("json")) {
			return this.get("json").asBoolean();
		}
		return true;
	}

	/**
	 * Sets the path. (Required)
	 * 
	 * @param path
	 *            the new path
	 */
	public void setPath(final String path) {
		this.put("path", path);
	}

	/**
	 * Gets the path.
	 * 
	 * @return the path
	 */
	public String getPath() {
		if (this.has("path")) {
			return this.get("path").asText();
		}
		LOG.warning("Config parameter 'path' missing in State "
				+ "configuration. Using the default path '.eveagents'");
		return ".eveagents";
	}

}
