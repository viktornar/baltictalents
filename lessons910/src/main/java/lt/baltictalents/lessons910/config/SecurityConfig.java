package lt.baltictalents.lessons910.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
  
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/register", "js", "css").permitAll()
            .antMatchers("/api/carts").hasAuthority("USER")
            .antMatchers("/api/items").hasAuthority("USER")
            .antMatchers("/api/users").hasAuthority("USER")
            .anyRequest().authenticated()
        .and()
            .formLogin().loginPage("/login").permitAll()
            .failureUrl("/login?error")
            .defaultSuccessUrl("/", true)
            .usernameParameter("username")
            .passwordParameter("password")
        .and()
            .logout().permitAll()
            .logoutRequestMatcher(
                new AntPathRequestMatcher("/logout")
            )
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
        .and()
            .exceptionHandling().authenticationEntryPoint(
                (request, response, authException) -> 
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            )
        .and()
            .csrf();
    }
}
