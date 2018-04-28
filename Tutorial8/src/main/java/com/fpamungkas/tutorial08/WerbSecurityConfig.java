package com.fpamungkas.tutorial08;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WerbSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/student/viewall").hasAuthority("ADMIN")
		.antMatchers("/student/view/**").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/student/viewall").hasRole("ADMIN")
		.antMatchers("/student/view/**").hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin")
//			.authorities("ADMIN");
//		
//		auth.inMemoryAuthentication()
//			.withUser("user").password("{noop}user")
//			.authorities("USER");
//	}
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentification(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled from users where username  = ?")
		.authoritiesByUsernameQuery("select username, role from user_roles where username = ?");
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
}
