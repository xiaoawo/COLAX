package com.myth.theseus.helper;

import com.myth.theseus.helper.gate.GateAspect;
import com.myth.theseus.helper.gate.GateProperties;
import com.myth.theseus.helper.gate.filter.GateFilter;
import com.myth.theseus.helper.gate.filter.wrapper.GateFilterWrappers;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;


@Configuration
@ComponentScan("com.myth.theseus.helper")
@EnableConfigurationProperties(GateProperties.class)
public class HelperAutoConfiguration {

	@Resource
	private GateProperties gateProperties;

	@Resource
	private Map<String, GateFilter> gateFilterMap;

	@Bean
	@ConditionalOnMissingBean
	public GateAspect gateAspect() {
		return new GateAspect(GateFilterWrappers.buildGateFilterWrapperChain(gateFilterMap,
				Optional.ofNullable(gateProperties).map(GateProperties::getFilters).orElse(null)));
	}
}
