package com.example.springjwt.config;

import com.example.springjwt.jwt.JWTFilter;
import com.example.springjwt.jwt.JWTUtil;
import com.example.springjwt.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((auth) -> auth.disable());
        http.formLogin((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login", "/", "/join").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        );

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JWTFilter(jwtUtil), LoginFilter.class);

        http.cors((corsCustomizer) -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();

                config.setAllowedOrigins(Collections.singletonList("localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowedHeaders(Collections.singletonList("*"));
                // 프론트에서 작업했으면 해주어야함
                config.setAllowCredentials(true);
                config.setMaxAge(3600L);

                // 헤더에 보낼 정보 허용(key값 지정)
                config.setExposedHeaders(Collections.singletonList("Authorization"));

                return config;
            }
        }));

        // JWT는 session을 stateless상태로 이용해야한다.
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
