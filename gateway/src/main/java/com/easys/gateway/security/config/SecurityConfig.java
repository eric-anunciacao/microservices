package com.easys.gateway.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.easys.course.core.property.JwtConfiguration;
import com.easys.gateway.security.filter.GatewayJwtTokenAuthorizationFilter;
import com.easys.security.config.SecurityTokenConfig;
import com.easys.security.token.converter.TokenConverter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {
	private final TokenConverter tokenConverter;

	public SecurityConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
		super(jwtConfiguration);
		this.tokenConverter = tokenConverter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
				UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}
}
