package com.training.basicauthentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource datasource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
					http.authorizeRequests()
						.antMatchers("/","/home","/user")
						.permitAll()
						.antMatchers("/user").permitAll()
						.antMatchers(HttpMethod.POST).permitAll()
						.anyRequest().authenticated()
						.and().formLogin().loginPage("/login").permitAll()
						.and().logout().permitAll().and().csrf().disable();
						
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configAuthuntication(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication()
		 .passwordEncoder(new BCryptPasswordEncoder())
		 .dataSource(datasource).usersByUsernameQuery("select username,password,enabled from users where username = ?")
		 .authoritiesByUsernameQuery("select username,role from users where username=?");
		
		
	}
	
	

}
