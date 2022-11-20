package com.se.authservice.security.jwt;

import com.se.backend.ecommerceapp.exceptions.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

	@Autowired
	private final JwtProvider jwtProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = jwtProvider.resolveToken((HttpServletRequest) request);

		try {
			if (token != null && jwtProvider.validateToken(token)) {
				Authentication authentication = jwtProvider.getAuthentication(token);

				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (JwtAuthenticationException e) {
			SecurityContextHolder.clearContext();
			((HttpServletResponse) response).sendError(e.getHttpStatus().value());
			throw new JwtAuthenticationException("JWT token is expired or invalid");
		}

		filterChain.doFilter(request, response);
	}
}
