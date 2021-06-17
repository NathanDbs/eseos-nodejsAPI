package com.eseos.tempoback.config;

import com.eseos.tempoback.model.enums.AuthorityEnum;
import com.eseos.tempoback.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@CrossOrigin(origins = "*", maxAge = 3600000)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("3600")
    private int serverRememberMeTimeout;

    @Autowired
    private AuthentificationSuccessHandlerImpl authentificationSuccessHandler;

    @Autowired
    private AuthentificationFailureHandlerImpl authentificationFailureHandler;

    @Autowired
    private LogoutSucessHandlerImpl logoutSucessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bower_components/**")
                .antMatchers("/fonts/**")
                .antMatchers("/images/**")
                .antMatchers("/scripts/**")
                .antMatchers("/styles/**")
                .antMatchers("/views/**")
                .antMatchers("/i18n/**")
                .antMatchers("/api/**","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authentificationSuccessHandler)
                .failureHandler(authentificationFailureHandler)
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSucessHandler)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(serverRememberMeTimeout)
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/**","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/user/resetPassword/**").permitAll()
                .antMatchers("/user/current").authenticated()
                .antMatchers("/user/**").authenticated()
                //.antMatchers("/**").authenticated()
                .antMatchers("/metrics/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/health/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/trace/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/dump/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/shutdown/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/beans/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/configprops/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/info/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/autoconfig/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/env/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/trace/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/api-docs/**").hasAuthority(AuthorityEnum.ADMIN.getAuthority())
                .antMatchers("/protected/**").authenticated();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
