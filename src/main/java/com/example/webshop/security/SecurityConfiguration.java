package com.example.webshop.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/brand").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/brand/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/discount").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/item").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/item/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/item/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/api/order/**").hasRole("ADMIN")
                    .antMatchers("/api/report/**").hasRole("ADMIN")
                    .and()
                    .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
