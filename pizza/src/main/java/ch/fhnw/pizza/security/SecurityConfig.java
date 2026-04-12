package ch.fhnw.madnessevents.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {
        //Create two users with different roles and add them to the in-memory user store

        return new InMemoryUserDetailsManager(
            User.withUsername("myuser")
                //.password("{noop}password") //create user with an encrypted password instead of the plain text password
                .password("{bcrypt}$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su")
                .authorities("READ","ROLE_USER")
                .build(), 
            User.withUsername("myadmin")
                //.password("{noop}password") //create user with an encrypted password instead of the plain text password
                .password("{bcrypt}$2a$10$9fxQtdWuRaYn5UchAm5iAexbPi7tmRadnDogJwXPR9fVDJyt9g/su")
                .authorities("READ","ROLE_ADMIN")
                .build());

    }

   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/",
                            "/events",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/h2-console/**"
                    ).permitAll()
                    .requestMatchers("/menu").hasRole("USER")
                    .requestMatchers("/menu/pizzas/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .formLogin(withDefaults())
            .httpBasic(withDefaults())
            .build();
}



        
}
