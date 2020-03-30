package com.easys.course.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.easys.course.core.property.JwtConfiguration;
import com.easys.security.config.SecurityTokenConfig;
import com.easys.security.filter.JwtTokenAuthorizationFilter;
import com.easys.security.token.converter.TokenConverter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

	private final TokenConverter tokenConverter;

	public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
		super(jwtConfiguration);
		this.tokenConverter = tokenConverter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
				UsernamePasswordAuthenticationFilter.class);
		super.configure(http);
	}

}