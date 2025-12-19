package com.example.TitoSampleAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.name}")
    private String password;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // System.out.println("username : " + username);
        // System.out.println("Password : " + password);
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // @Bean
    // public AuthenticationEntryPoint authenticationEntryPoint() {
    // return (HttpServletRequest request, HttpServletResponse response,
    // org.springframework.security.core.AuthenticationException authException) -> {
    // response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // response.getWriter().write("Unauthorized");
    // };
    // }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException authException) -> {

            // System.out.println("PARAMETER : " + request.getParameterNames());
            // Enumeration<String> paramNames = request.getParameterNames();
            // while (paramNames.hasMoreElements()) {
            //     String paramName = paramNames.nextElement();
            //     System.out.println("PARAMETER: " + paramName + " = " + request.getParameter(paramName));
            // }

            // // Or get specific parameters
            // String gateslip = request.getParameter("gateslip");
            // String vehtype = request.getParameter("vehtype");
            // System.out.println("gateslip: " + gateslip + ", vehtype: " + vehtype);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            Map<String, Object> body = new HashMap<>();
            // body.put("timestamp", LocalDateTime.now().toString());
            body.put("status", 401);
            body.put("error", "Unauthorized");
            // body.put("message", authException.getMessage());
            // body.put("path", request.getRequestURI());

            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(body));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
                )
                // .httpBasic(basic ->
                // basic.authenticationEntryPoint(authenticationEntryPoint()))
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}

// http.authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/").permitAll()
//                 .anyRequest().authenticated())
//                 .httpBasic(Customizer.withDefaults())
//                 .csrf(csrf -> csrf.disable());
//         return http.build();