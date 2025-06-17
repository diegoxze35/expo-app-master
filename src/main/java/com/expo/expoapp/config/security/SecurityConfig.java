package com.expo.expoapp.config.security;

import com.expo.expoapp.config.jwt.JwtValidationFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
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
								auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
										//.requestMatchers("/api/teams").hasRole("STUDENT")
										.requestMatchers("/api/students").hasRole("STUDENT")
										.requestMatchers("/api/projects").hasRole("STUDENT")
										.requestMatchers("/api/projects/professors").hasRole("STUDENT")
										.requestMatchers("/api/professors").hasRole("PROFESSOR")
										.requestMatchers("/uploads/images/**").permitAll()
										.anyRequest()
										.authenticated()
				)
				.csrf(AbstractHttpConfigurer::disable)
				.cors(c -> c.configurationSource(this.corsConfigSource()))
				.sessionManagement(s ->
						s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationManager()))
				.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(List.of("*"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> filterRegistrationBean() {
		FilterRegistrationBean<CorsFilter> cordsBean = new FilterRegistrationBean<>(new CorsFilter(this.corsConfigSource()));
		cordsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return cordsBean;
	}

}
