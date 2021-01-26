package com.ssafy.wpwk.config;

import com.ssafy.wpwk.filters.JWTAuthenticationFilter;
import com.ssafy.wpwk.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.token}")
    private String secretKey;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        Filter filter = new JWTAuthenticationFilter(authenticationManager(), jwtUtil());

        http.formLogin().disable()
                .cors().disable()
                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/users/**").hasAuthority("ROLE_USER")
//                .anyRequest().authenticated().and()
                .headers().frameOptions().disable()
                .and()
                .addFilter(filter)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil(secretKey);
    }
}
