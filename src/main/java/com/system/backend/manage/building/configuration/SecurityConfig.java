package com.system.backend.manage.building.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.system.backend.manage.building.constant.SecurityConstant;
import com.system.backend.manage.building.security.AccessDeniedHandlerJwt;
import com.system.backend.manage.building.security.AuthenticationEntryPointJwt;
import com.system.backend.manage.building.security.AuthorizationFilterJwt;
import com.system.backend.manage.building.security.filter.CustomAuthenticationFilter;
import com.system.backend.manage.building.utils.GenerateToken;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public AuthorizationFilterJwt authorizationFilterJwt;
	@Autowired
	private AccessDeniedHandlerJwt accessDeniedHandlerJwt;
	@Autowired
	private AuthenticationEntryPointJwt authenticationEntryPointJwt;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private GenerateToken generateToken;

	

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(SecurityConstant.PUBLIC_URLS).permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandlerJwt);
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPointJwt);
		http.addFilter(customAuthenticationFilter());
		http.addFilterBefore(authorizationFilterJwt, UsernamePasswordAuthenticationFilter.class);
		/*
		 * http.authorizeRequests().antMatchers(HttpMethod.GET,
		 * "/api/persona/list/**").permitAll();
		 * http.authorizeRequests().antMatchers(HttpMethod.POST,
		 * "/api/auth/token/refresh/**").permitAll();
		 * http.authorizeRequests().antMatchers(HttpMethod.POST,
		 * "/api/auth/login").permitAll();
		 * http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/list/**").
		 * hasAnyAuthority("ROLE_USER");
		 * http.authorizeRequests().antMatchers(HttpMethod.POST,
		 * "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
		 */
		// http.authorizeRequests().anyRequest().permitAll();

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManagerBean(), generateToken);
		filter.setFilterProcessesUrl("/api/auth/login");
		return filter;
	}
	

}
