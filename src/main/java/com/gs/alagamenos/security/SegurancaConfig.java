package com.gs.alagamenos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SegurancaConfig {
	
	@Autowired
	private JWTAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filtrar(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable())
						.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
						.authorizeHttpRequests(request ->
					    request
					        .requestMatchers(
					        	"/auth/login",
					            "/swagger-ui/**",
					            "/v3/api-docs/**",
					            "/swagger-resources/**",
					            "/webjars/**"
					        ).permitAll()
					        .anyRequest().authenticated()
					)
						//.httpBasic(Customizer.withDefaults());
						.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
						.sessionManagement(session -> session
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}

}
