package myy803.project.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Lazy
@TestConfiguration
public class TestConfig extends SecurityConfig {

	@Override
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			// URL matching for accessibility
			.antMatchers("/", "/login", "/register", "/post/**").permitAll()
			.antMatchers("/student/**").hasAnyAuthority("STUDENT")
			.antMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.antMatchers("/password").hasAnyAuthority("STUDENT", "PROFESSOR", "ADMIN")
			.anyRequest().authenticated();
		http
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/post/login")
			.failureUrl("/login?LoginError=true")
			.successHandler(loginSuccessHandler);
		http
			.logout()
			.logoutSuccessUrl("/login.html")
			.invalidateHttpSession(true);

		return http.build();
	}
}
