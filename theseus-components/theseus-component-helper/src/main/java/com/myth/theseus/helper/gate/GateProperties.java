package com.myth.theseus.helper.gate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "myth.theseus.gate")
public class GateProperties {

	private static final String[] DEFAULT_FILTERS = {"catchExceptionGateFilter", "entranceLogGateFilter", "validationGateFilter"};

	private String[] filters;

	public String[] getFilters() {
		if (filters == null) {
			return DEFAULT_FILTERS;
		}

		return filters;
	}
}