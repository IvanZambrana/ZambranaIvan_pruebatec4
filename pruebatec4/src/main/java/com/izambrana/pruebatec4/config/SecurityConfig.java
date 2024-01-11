package com.izambrana.pruebatec4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                //Endpoints para admin
                                .requestMatchers("/agency/flights/new").authenticated()
                                .requestMatchers("/agency/hotels/new").authenticated()
                                .requestMatchers("/agency/flights/edit/**").authenticated()
                                .requestMatchers("/agency/hotels/edit/**").authenticated()
                                .requestMatchers("/agency/flights/delete/**").authenticated()
                                .requestMatchers("/agency/hotels/delete/**").authenticated()
                                .anyRequest().permitAll() //Cualquier otro Endpoint es publico
                )
                .formLogin(login -> login
                        .permitAll()
                )
                .httpBasic().and()
                .build();
    }
}
