package br.unisantos.livres;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		private static final String[] PUBLIC_MATCHERS = {
				"/categorias/**",
				"/produtors/**",
				"/produtos/**"
				//"/usuarios/**" 
		};
		
		private static final String[] PUBLIC_MATCHERS_POST = {
				"/usuarios/**"
		};
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.cors().and().csrf().disable();;
			http.authorizeRequests()
				//.antMatchers(PUBLIC_MATCHERS)
				.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS)
				.permitAll()
				.antMatchers(HttpMethod.POST,PUBLIC_MATCHERS_POST)
				.permitAll()
				.anyRequest()
				.authenticated();
			
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
		
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
		 final UrlBasedCorsConfigurationSource source = new	 UrlBasedCorsConfigurationSource();
		 	
		 source.registerCorsConfiguration("/**", new
		 CorsConfiguration().applyPermitDefaultValues());
		 
		 return source;
		}
		
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
		 return new BCryptPasswordEncoder();
		}
}
