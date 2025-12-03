package com.pos.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/swagger-ui.html",
          "/swagger-ui/**",
          "/v3/api-docs/**"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .httpBasic(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails professor = User
      .withUsername("professor")
      .password("{noop}demo1234")
      .roles("ADMIN")
      .build();

    UserDetails demo = User
      .withUsername("demo")
      .password("{noop}demo1234")
      .roles("USER")
      .build();

    return new InMemoryUserDetailsManager(professor, demo);
  }
}
