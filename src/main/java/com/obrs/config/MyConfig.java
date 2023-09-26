package com.obrs.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {
	@Bean
	public RestTemplate restTemplate() {
	return new RestTemplate();
		}

}
