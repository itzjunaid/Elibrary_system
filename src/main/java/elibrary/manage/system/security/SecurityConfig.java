package elibrary.manage.system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final UserdetailsServiceImple userdetailsServiceImple;
	
	
	
	    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			UserdetailsServiceImple userdetailsServiceImple) {
	
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userdetailsServiceImple = userdetailsServiceImple;
	}


		@Bean
	   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	        return http
	                .csrf(AbstractHttpConfigurer::disable)
	                .authorizeHttpRequests(
	                        req->req.requestMatchers("/admin/login/**","/admin/register/**")
	                                .permitAll()
	                                .requestMatchers("/admin/books/**").hasAuthority("ADMIN")
	                                .requestMatchers("/library/**").hasAuthority("USER")
	                                .anyRequest()
	                                .authenticated()
	                ).userDetailsService(userdetailsServiceImple)
	                .sessionManagement(session->session
	                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
	                .build();

	    }

	
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
}
