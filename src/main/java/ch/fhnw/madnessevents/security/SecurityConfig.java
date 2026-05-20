package ch.fhnw.madnessevents.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public pages
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/djs.html",
                    "/dj-detail.html",
                    "/tickets.html",
                    "/event-detail.html",
                    "/shop.html",
                    "/product-detail.html",
                    "/cart.html",
                    "/privacy.html",
                    "/terms.html",
                    "/cookies.html",
                    "/ticket-tc.html",
                    "/vulnerability.html",
                    "/press.html",
                    "/login.html",
                    "/login",
                    "/error",
                    "/hello",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**"
                ).permitAll()
                // Public API reads
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                // Writes require admin
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                // Admin pages
                .requestMatchers("/admin.html", "/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl("/perform-login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin.html", true)
                .failureUrl("/login.html?error")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        // Allow H2 console iframe
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
