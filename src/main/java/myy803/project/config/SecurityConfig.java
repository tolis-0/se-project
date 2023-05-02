package myy803.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomSecuritySuccessHandler customSecuritySuccessHandler;
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			// URL matching for accessibility
			.antMatchers("/", "/login").permitAll()
			.antMatchers("/student/**").hasAnyAuthority("STUDENT")
			.antMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
			.anyRequest().authenticated();
		http
			.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error=true")
			.successHandler(customSecuritySuccessHandler);
		

		return http.build();
	}
}
