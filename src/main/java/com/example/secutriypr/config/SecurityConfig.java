package com.example.secutriypr.config;

import jakarta.servlet.annotation.WebListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@WebListener
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers("/memberuser/login").permitAll()
                                .requestMatchers("/board/register").authenticated()
                                .anyRequest().permitAll()
                )
                .csrf( csrf -> csrf.disable())

                .formLogin(
                        formLogin -> formLogin.loginPage("/memberuser/login")
                                .defaultSuccessUrl("/")
                                .usernameParameter("email")
                )

                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/memberuser/logout"))
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                );


        return httpSecurity.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
