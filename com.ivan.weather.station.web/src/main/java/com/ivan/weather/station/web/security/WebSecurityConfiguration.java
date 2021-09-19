package com.ivan.weather.station.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ivan.weather.station.persistence.service.api.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	
	@Autowired
	public WebSecurityConfiguration(UserService userService) {
		this.userService = userService;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/raspberry")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userService);
    }

}
