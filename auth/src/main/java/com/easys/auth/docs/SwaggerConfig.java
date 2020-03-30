package com.easys.auth.docs;

import org.springframework.context.annotation.Configuration;

import com.easys.course.core.docs.BaseSwaggerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	public SwaggerConfig() {
		super("com.easys.auth.endpoint.controller");
	}

}
