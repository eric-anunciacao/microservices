package com.easys.gateway.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;

import com.easys.course.core.property.JwtConfiguration;
import com.easys.security.filter.JwtTokenAuthorizationFilter;
import com.easys.security.token.converter.TokenConverter;
import com.easys.security.util.SecurityContextUtil;
import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;

import lombok.SneakyThrows;

public class GatewayJwtTokenAuthorizationFilter extends JwtTokenAuthorizationFilter {

	public GatewayJwtTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
		super(jwtConfiguration, tokenConverter);
	}

	@Override
	@SneakyThrows
	@SuppressWarnings("Duplicates")
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain chain) throws ServletException, IOException {
		String header = request.getHeader(jwtConfiguration.getHeader().getName());

		if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();

		String signedToken = tokenConverter.decryptToken(token);

		tokenConverter.validateTokenSignature(signedToken);

		SecurityContextUtil.setSecurityContext(SignedJWT.parse(signedToken));

		if (jwtConfiguration.getType().equalsIgnoreCase("signed"))
			RequestContext.getCurrentContext().addZuulRequestHeader("Authorization",
					jwtConfiguration.getHeader().getPrefix() + signedToken);

		chain.doFilter(request, response);
	}

}
