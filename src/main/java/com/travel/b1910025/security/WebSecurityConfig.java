package com.travel.b1910025.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import
org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.travel.b1910025.models.User;
import com.travel.b1910025.security.jwt.AuthEntryPointJwt;
import com.travel.b1910025.security.jwt.AuthTokenFilter;
import com.travel.b1910025.security.services.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
// securedEnabled = true,
// jsr250Enabled = true,
prePostEnabled = true)
public class WebSecurityConfig {
@Autowired
UserDetailsServiceImpl userDetailsService;

@Autowired
private AuthEntryPointJwt unauthorizedHandler;

@Bean
public AuthTokenFilter authenticationJwtTokenFilter() {
return new AuthTokenFilter();
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

authProvider.setUserDetailsService(userDetailsService);
authProvider.setPasswordEncoder(passwordEncoder());

return authProvider;
}

@Bean
public AuthenticationManager
authenticationManager(AuthenticationConfiguration authConfig) throws
Exception {
return authConfig.getAuthenticationManager();
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.cors().and().csrf().disable()
.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
.authorizeRequests().antMatchers("/api/auth/**").permitAll()
.antMatchers("/api/tour/**").permitAll()
.antMatchers("/api/address/**").permitAll()
.antMatchers("/api/addressSecond/**").permitAll()
.antMatchers("/api/hotel/**").permitAll()
.antMatchers("/api/place/**").permitAll()
.antMatchers("/api/restau/**").permitAll()
.antMatchers("/api/firm/**").permitAll()
.antMatchers("/api/nhanvien/**").permitAll()
.antMatchers("/api/cart/**").permitAll()
.antMatchers("/api/category/**").permitAll()
.antMatchers("/api/search/**").permitAll()
.antMatchers("/api/invoice/**").permitAll()
.antMatchers("/api/payment/**").permitAll()
.antMatchers("/api/admin/**").permitAll()
.anyRequest().authenticated();

http.authenticationProvider(authenticationProvider());

http.addFilterBefore(authenticationJwtTokenFilter(),
UsernamePasswordAuthenticationFilter.class);

return http.build();
}

}
