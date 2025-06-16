package com.expo.expoapp.config.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtValidationFilter extends BasicAuthenticationFilter {

	private final ObjectMapper objectMapper;

	public JwtValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.objectMapper = new ObjectMapper();
	}

	private static class SimpleGrantedAuthorityJsonCreator {

		@JsonCreator
		public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JWT.AUTH_HEADER);
		if (header == null || !header.startsWith(JWT.BEARER_TOKEN)) {
			chain.doFilter(request, response);
			return;
		}
		String token = header.replace(JWT.BEARER_TOKEN, "");
		try {
			Claims claims = Jwts.parser()
					.verifyWith(JWT.SECRET_KEY)
					.build()
					.parseSignedClaims(token)
					.getPayload();
			String matriculate = claims.getSubject();
			Object authorities = claims.get("roles");
			Collection<? extends GrantedAuthority> roles = Arrays.asList(
					objectMapper
							.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
							.readValue(authorities.toString().getBytes(), SimpleGrantedAuthority[].class)
			);
			UsernamePasswordAuthenticationToken u = new UsernamePasswordAuthenticationToken(
					matriculate, null, roles
			);
			SecurityContextHolder.getContext().setAuthentication(u);
			System.out.println("JWT doFilterInternal END");
			chain.doFilter(request, response);
		} catch (JwtException e) {
			Map<String, String> body = new HashMap<>();
			body.put("error", e.getMessage());
			response.getWriter().write(objectMapper.writeValueAsString(body));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType(JWT.CONTENT_TYPE);
		}

	}
}
