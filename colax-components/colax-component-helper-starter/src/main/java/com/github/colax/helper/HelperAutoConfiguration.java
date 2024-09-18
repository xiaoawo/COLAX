package com.github.colax.helper;

import com.github.colax.helper.gate.GateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.github.colax.helper")
@EnableConfigurationProperties(GateProperties.class)
public class HelperAutoConfiguration {

}
