package com.regency.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatchers;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JWTAuthenticationEntryPoint jwtEntryPoint;

	@Autowired
	JWTAuthentciationFilter jwtFilter;

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((auth) -> auth.
						requestMatchers(HttpMethod.GET, "/doctor/**").permitAll()
						.requestMatchers(HttpMethod.GET , "/swagger-ui/**").permitAll()
						.requestMatchers(HttpMethod.GET , "/v3/api-docs/swagger-config").permitAll()
						.requestMatchers(HttpMethod.GET , "/v3/api-docs").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
						.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtEntryPoint))
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.builder().username("Rajat").password(passwordEncoder().encode("Rajat")).roles("USER")
//				.build();
//		UserDetails admin = User.builder().username("Admin").password(passwordEncoder().encode("Admin")).roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}

}
