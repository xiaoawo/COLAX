package com.github.colax.helper.gate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "colax.gate")
public class GateProperties {
	private String[] filters;

	public String[] getFilters() {
		if (filters == null) {
			return new String[]{};
		}

		return filters;
	}
}