package com.github.colax.helper;

import com.github.colax.helper.gate.GateAspect;
import com.github.colax.helper.gate.GateProperties;
import com.github.colax.helper.gate.filter.GateFilter;
import com.github.colax.helper.gate.filter.wrapper.GateFilterWrappers;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
@ComponentScan("com.github.colax.helper")
@EnableConfigurationProperties(GateProperties.class)
public class HelperAutoConfiguration {

	@Resource
	private GateProperties gateProperties;

	@Resource

	private Map<String, GateFilter> gateFilterMap;

	@Bean
	@ConditionalOnMissingBean
	public GateAspect gateAspect() {
		return new GateAspect(GateFilterWrappers.buildGateFilterWrapperChain(gateFilterMap, gateProperties.getFilters()));
	}
}
