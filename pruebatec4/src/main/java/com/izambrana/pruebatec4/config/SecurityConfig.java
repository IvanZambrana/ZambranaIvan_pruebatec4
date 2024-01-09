package com.izambrana.pruebatec4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

/*
                                // Endpoints públicos (sin autenticación)
                                        "/agency/flights",
                                        "/agency/hotels",
                                        "/agency/flights/{id}",
                                        "/agency/hotels/{id}",
                                        "/agency/flights/search",
                                        "/agency/hotels/search",
                                        "/agency/hotel-booking/new",
                                        "/agency/flight-booking/new"

                                // Endpoints protegidos (requieren autenticación)
                                        "/agency/hotels/new",
                                        "/agency/hotels/edit/{id}",
                                        "/agency/flights/new",
                                        "/agency/flights/edit/{id}",
                                        "/agency/flights/delete/{id}",
                                        "/agency/hotels/delete/{id}",
                                        /agency/hotel-booking/all,
                                        /agency/hotel-booking/{id},
                                        /agency/hotel-booking/delete/{id},
                                        /agency/hotel-booking/edit/{id},
                                        /agency/flight-booking/all,
                                        /agency/flight-booking/{id},
                                        /agency/flight-booking/delete/{id},
                                        /agency/flight-booking/edit/{id}
                               */

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();

        /*
        *  return http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(
                        "/agency/flights",
                        "/agency/hotels",
                        "/agency/flights/{id}",
                        "/agency/hotels/{id}",
                        "/agency/flights/search",
                        "/agency/hotels/search",
                        "/agency/hotel-booking/new",
                        "/agency/flight-booking/new"
                    )
                    .permitAll()  // Endpoints públicos (sin autenticación)
                    .antMatchers(
                        "/agency/hotels/new",
                        "/agency/hotels/edit/{id}",
                        "/agency/flights/new",
                        "/agency/flights/edit/{id}",
                        "/agency/flights/delete/{id}",
                        "/agency/hotels/delete/{id}",
                        "/agency/hotel-booking/all",
                        "/agency/hotel-booking/{id}",
                        "/agency/hotel-booking/delete/{id}",
                        "/agency/hotel-booking/edit/{id}",
                        "/agency/flight-booking/all",
                        "/agency/flight-booking/{id}",
                        "/agency/flight-booking/delete/{id}",
                        "/agency/flight-booking/edit/{id}"
                    )
                    .authenticated()  // Endpoints protegidos (requieren autenticación)
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
                */
    }

    //Método encargado de la información del usuario autenticado
    @Bean
    UserDetailsService userDetailService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles()
                .build());
        return manager;
    }

    //Método para codificar la contraseña
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService())
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}