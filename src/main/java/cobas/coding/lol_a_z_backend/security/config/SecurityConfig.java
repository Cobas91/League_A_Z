package cobas.coding.lol_a_z_backend.security.config;

import cobas.coding.lol_a_z_backend.security.filter.JwtAuthFilter;
import cobas.coding.lol_a_z_backend.security.service.SummonerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final SummonerDetailService summonerDetailService;
	private final JwtAuthFilter jwtAuthFilter;

	@Autowired public SecurityConfig(SummonerDetailService summonerDetailService, JwtAuthFilter jwtAuthFilter) {
		this.summonerDetailService = summonerDetailService;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(summonerDetailService);
	}

	@Override protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/auth/**").permitAll().antMatchers("/oauth/**").permitAll().antMatchers("/api/**").authenticated().antMatchers(
                "/**")
				.permitAll().and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Bean public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override @Bean public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
