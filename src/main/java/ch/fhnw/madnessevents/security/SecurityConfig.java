package ch.fhnw.madnessevents.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                         
        "/",
        "/index.html",
        "/djs.html",
        "/tickets.html",
        "/shop.html",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/h2-console/**",
        "/hello",
        "/error",
        "/css/**",
        "/js/**",
        "/images/**"
        ).permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        .requestMatchers("/admin.html").authenticated()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

    http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

    return http.build();
}
@Bean
public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(admin);
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}