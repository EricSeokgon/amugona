package tk.dbcore.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Project: amugona
 * FileName: WebSecurityConfig
 * Date: 2015-09-25
 * Time: 오후 4:55
 * Author: Hadeslee
 * Note:
 * To change this template use File | Settings | File Templates.
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic();

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("USER")
                .anyRequest().permitAll();
    }

    // ROLE Hieracry
}
