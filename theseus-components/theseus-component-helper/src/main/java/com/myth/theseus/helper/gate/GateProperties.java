package com.myth.theseus.helper.gate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myth.theseus.gate")
public class GateProperties {
	private String[] filters;

	public String[] getFilters() {
		if (filters == null) {
			return new String[]{};
		}

		return filters;
	}
}