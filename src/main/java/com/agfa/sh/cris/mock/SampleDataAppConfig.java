package com.agfa.sh.cris.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.agfa.sh.cris.mock.service.WorkitemIdCache;

@Configuration
public class SampleDataAppConfig {

	@Bean(name="workitemIdCache", initMethod="init", destroyMethod= "destroy")
	@Scope("singleton")
	public WorkitemIdCache workitemIdCache() {
		return new WorkitemIdCache();
	}
}
