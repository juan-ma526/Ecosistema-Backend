package com.semillero.ecosistema.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private UserDetailsService userDetailsService;

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // Habilitar CORS usando la configuración proporcionada por CorsConfig
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Permitir acceso a todos los endpoints sin autenticación
            )
            .oauth2Login().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}