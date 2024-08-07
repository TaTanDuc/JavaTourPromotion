package com.team12.JavaTourPromotion;
import  com.team12.JavaTourPromotion.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
                auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http)
            throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/home/**","/api/v1/user/**","/api/v1/inspector/**","/api/v1/admin/**","/api/v1/security/**").permitAll()//.hasAuthority("ADMIN")
//                        .requestMatchers("/api/user/**","/api/inspector/**").hasAuthority("INSPECTOR")
//                        .requestMatchers("/api/user/**").hasAuthority("USER")
//                        .requestMatchers("/api/home/**").permitAll()
                                .anyRequest().permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/destinations")
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                                .key("promotionTourSecurity")
                                .rememberMeCookieName("promotionTourSecurity")
                                .tokenValiditySeconds(24 * 60 * 60)
                .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403")
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1)
                        .expiredUrl("/login")
                )
                .httpBasic(httpBasic -> httpBasic
                        .realmName("promotionTourSecurity")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
        }
    }
