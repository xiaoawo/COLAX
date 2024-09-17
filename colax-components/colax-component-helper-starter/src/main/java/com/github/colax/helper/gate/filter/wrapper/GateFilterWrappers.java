package com.github.colax.helper.gate.filter.wrapper;


import com.github.colax.helper.gate.filter.GateFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

@Slf4j
public class GateFilterWrappers {

	public static GateFilterWrapper buildGateFilterWrapperChain(Map<String, GateFilter> filterMap, String[] filterNames) {
		if (MapUtils.isEmpty(filterMap)) {
			return buildGateFilterCoreWrapper();
		}

		if (filterNames == null || filterNames.length == 0) {
			return buildGateFilterCoreWrapper();
		}

		GateFilterWrapper last = buildGateFilterCoreWrapper();
		for (int i = filterNames.length - 1; i >= 0; i--) {
			GateFilter gateFilter = filterMap.get(filterNames[i]);
			if (gateFilter == null) {
				log.warn("{} not found", filterNames[i]);
				continue;
			}

			last = new GateFilterWrapper(gateFilter, last);
		}

		return last;
	}

	private static GateFilterWrapper buildGateFilterCoreWrapper() {
		return new GateFilterCoreWrapper();
	}
}