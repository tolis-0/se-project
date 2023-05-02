package myy803.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) 
    		throws Exception {
        return authConfig.getAuthenticationManager();
    }
	
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
			.successHandler(loginSuccessHandler);
		

		return http.build();
	}
}
