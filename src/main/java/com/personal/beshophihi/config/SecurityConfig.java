package com.personal.beshophihi.config;

import com.personal.beshophihi.security.jwt.JwtEntryPoint;
import com.personal.beshophihi.security.jwt.JwtFilter;
import com.personal.beshophihi.security.userPrincipal.UserDetailServiceIMPL;
import com.personal.beshophihi.utils.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailServiceIMPL userDetailServiceIMPL;
    private final JwtFilter jwtFilter;
    private final JwtEntryPoint jwtEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailServiceIMPL);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/auth/login", "/api/v1/auth/sign-up").permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "/api/v1/brand/**",
                                        "/api/v1/discount/**",
                                        "/api/v1/address-user/**",
                                        "/api/v1/image/**",
                                        "/api/v1/order/**",
                                        "/api/v1/product/**",
                                        "/api/v1/review/**",
                                        "/api/v1/cart/**",
                                        "/api/v1/status-order/**",
                                        "/api/v1/type-product/**",
                                        "/api/v1/user/**",
                                        "/api/v1/report/**",
                                        "/api/v1/payment/**"
                                ).permitAll()

                                .requestMatchers(HttpMethod.POST,
                                        "/api/v1/order/**",
                                        "/api/v1/review/**",
                                        "/api/v1/cart/**"
                                ).hasAuthority(RoleName.CUSTOMER.name())

                                .requestMatchers(HttpMethod.PUT,
                                        "/api/v1/cart/**"
                                ).hasAuthority(RoleName.CUSTOMER.name())

                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/v1/cart/**"
                                ).hasAuthority(RoleName.CUSTOMER.name())

                                .requestMatchers(HttpMethod.GET,
                                        "/api/v1/stock-receipt/**",
                                        "/api/v1/supplier/**"
                                ).hasAuthority(RoleName.ADMIN.name())

                                .requestMatchers(HttpMethod.POST,
                                        "/api/v1/brand/**",
                                        "/api/v1/discount/**",
                                        "/api/v1/image/**",
                                        "/api/v1/status-order/**",
                                        "/api/v1/stock-receipt/**",
                                        "/api/v1/supplier/**",
                                        "/api/v1/type-product/**"
                                ).hasAuthority(RoleName.ADMIN.name())

                                .requestMatchers(HttpMethod.PUT,
                                        "/api/v1/brand/**",
                                        "/api/v1/discount/**",
                                        "/api/v1/supplier/**",
                                        "/api/v1/type-product/**"
                                ).hasAuthority(RoleName.ADMIN.name())

                                .requestMatchers(HttpMethod.PATCH,
                                        "/api/v1/brand/**",
                                        "/api/v1/order/**",
                                        "/api/v1/type-product/**"
                                ).hasAuthority(RoleName.ADMIN.name())

                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/v1/brand/**",
                                        "/api/v1/discount/**",
                                        "/api/v1/supplier/**",
                                        "/api/v1/type-product/**"
                                ).hasAuthority(RoleName.ADMIN.name())

                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtEntryPoint))
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
