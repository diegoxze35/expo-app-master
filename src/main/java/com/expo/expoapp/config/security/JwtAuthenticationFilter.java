package com.expo.expoapp.config.security;

import com.expo.expoapp.config.jwt.JWT;
import com.expo.expoapp.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	private final ObjectMapper objectMapper;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.objectMapper = new ObjectMapper();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginRequest loginRequest;
		try {
			loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get authentication data", e);
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequest.email(),
				loginRequest.password()
		);

		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult
	) throws IOException {

		User user = (User) authResult.getPrincipal();
		String username = user.getUsername();
		Collection<? extends GrantedAuthority> roles = user.getAuthorities();

		Claims claims = Jwts
				.claims()
				.add("roles", objectMapper.writeValueAsString(roles))
				.build();

		String token = Jwts
				.builder()
				.subject(username)
				.expiration(new Date(System.currentTimeMillis() + 86_400_000)) //1 day
				.issuedAt(new Date())
				.claims(claims)
				.signWith(JWT.SECRET_KEY)
				.compact();

		response.addHeader(JWT.AUTH_HEADER, JWT.BEARER_TOKEN + token);
		Map<String, String> body = new HashMap<>();
		body.put("token", token);
		body.put("userType", roles.toArray()[0].toString());

		response.getWriter().write(objectMapper.writeValueAsString(body));
		response.setContentType(JWT.CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                          AuthenticationException failed) throws IOException {
		Map<String, String> body = new HashMap<>();
		body.put("error", failed.getMessage());
		response.getWriter().write(objectMapper.writeValueAsString(body));
		response.setContentType(JWT.CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
