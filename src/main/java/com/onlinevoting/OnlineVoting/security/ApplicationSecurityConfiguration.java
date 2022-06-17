package com.onlinevoting.OnlineVoting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // default /login is public
        http.authorizeRequests().antMatchers("/login","/token/refresh").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/user").permitAll();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("ROLE_SUPER_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority("ROLE_SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "/user/admin").hasAnyAuthority("ROLE_SUPER_ADMIN")
                .antMatchers("/role/**").hasAuthority("ROLE_SUPER_ADMIN")
                .antMatchers("/election/**").hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/party/**").hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/vote/**").hasAnyAuthority("ROLE_SUPER_ADMIN", "ROLE_ADMIN", "ROLE_USER")
                .anyRequest()
                .authenticated();

        //filter
        AuthenticationFilter filter = new AuthenticationFilter(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        http.addFilter(filter);
        http.addFilterBefore(new AuthorizationFilter(), AuthenticationFilter.class);
    }
}