package io.raut.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
//		builder.inMemoryAuthentication()
//				.withUser("foo")
//				.password("foo")
//				.roles("USER")
//				.and().withUser("blah")
//				.password("blah")
//				.roles("ADMIN");
		
	//	builder.jdbcAuthentication().dataSource(dataSource);
//			.withDefaultSchema()
//			.withUser(
//						User.withUsername("foo")
//						.password("foo")
//						.roles("USER")
//					)
//			.withUser(
//					User.withUsername("blah")
//					.password("blah")
//					.roles("ADMIN")
//				);
		
		builder.userDetailsService(userDetailsService);
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER","ADMIN")
			.antMatchers("/").permitAll()			
			.and().formLogin();
	}
}
