package com.expo.expoapp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

	private final AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	public ConfiguracionSeguridad(AuthenticationConfiguration authenticationConfiguration) {
		this.authenticationConfiguration = authenticationConfiguration;
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return this.authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(
						(auth) ->
								auth.requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
										.requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
										.anyRequest()
										.authenticated()
				)
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(s ->
						s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.addFilter(new JwtFiltroAutenticador(authenticationManager()))
				.build();
	}

}
