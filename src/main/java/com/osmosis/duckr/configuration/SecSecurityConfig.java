package com.osmosis.duckr.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
@Profile("!https")
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationSuccessHandler successHandler;

	/*@Autowired
	UserDetailsServiceCustom userDetailsServiceCustom;

	@Override
	public void configure(AuthenticationManagerBuilder builder) {
		builder.authenticationProvider(authenticationProvider());
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServiceCustom);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	private static String REALM = "DUCKR";

	@Override
	protected void configure(final HttpSecurity http)
		throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(
				HttpMethod.GET,
				"/index*", "/static/**", "/*.js", "/*.json", "/*.ico"
			)
			.permitAll()
			.and()

			.authorizeRequests()
			.antMatchers(
				HttpMethod.POST, "/user/register"
			).permitAll()
			.anyRequest()
			.authenticated()
			.and()

			.httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
			.and()

			.formLogin()
				.successHandler(successHandler)
			.loginPage("/login")
			.loginProcessingUrl("/user/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()

			.and().sessionManagement().disable()
		;

	}

	/* To allow Pre-flight [OPTIONS] request from browser */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
