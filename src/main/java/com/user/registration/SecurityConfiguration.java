//package com.user.registration;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// 
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
////@ComponentScan
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// 
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
////		and().authorizeRequests().
////			antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')").
////			antMatchers("/api/user/**").access("hasRole('ROLE_USER')");
////		http.csrf().disable();
//    }
// 
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//       // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//    }
//}