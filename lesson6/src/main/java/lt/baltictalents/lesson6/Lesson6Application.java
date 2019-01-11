package lt.baltictalents.lesson6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SpringBootApplication
public class Lesson6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson6Application.class, args);
	}

}

 @Configuration
 class SecurityConfig extends WebSecurityConfigurerAdapter {
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
     }

     @Autowired
     private UserDetailsService userDetailsService;

     @Bean
     public BCryptPasswordEncoder bCryptPasswordEncoder() {
         return new BCryptPasswordEncoder();
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
             .authorizeRequests()
                 .antMatchers("/vendor/**").permitAll()
                 .antMatchers("/css/**").permitAll()
                 .antMatchers("/img/**").permitAll()
                 .antMatchers("/js/**").permitAll()
                 .antMatchers("/api/**").permitAll()
                 .anyRequest().authenticated()
                 .antMatchers("/**").hasAnyAuthority("ANONYMOUS", "READER", "WRITER", "ADMIN")
                 .antMatchers("/admin/**").hasAuthority("ADMIN")
             .and()
                 .formLogin()
                     .loginPage("/login")
                     .permitAll()
                     .failureUrl("/login?error")
                     .defaultSuccessUrl("/")
                     .usernameParameter("username").passwordParameter("password")
             .and()
                 .logout()
                     .permitAll()
                     .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                     .invalidateHttpSession(true)
                     .clearAuthentication(true)
             .and()
                 .exceptionHandling().accessDeniedPage("/403")
                 .and()
             .csrf();
     }

     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
     }
 }