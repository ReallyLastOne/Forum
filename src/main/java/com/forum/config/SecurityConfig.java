package com.forum.config;

import com.forum.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());
        http.authorizeRequests().antMatchers("/h2/**").permitAll()
                .antMatchers("/any").hasAnyAuthority("USER", "ADMIN", "MODERATOR")
                .antMatchers("/authenticated").authenticated()
                .antMatchers("/admin").hasAnyAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().and().logout().disable();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }
}
