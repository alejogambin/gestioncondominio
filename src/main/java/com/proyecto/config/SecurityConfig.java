package com.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.proyecto.app.service.impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UsuarioServiceImpl userDetailsService;

    public SecurityConfig(UsuarioServiceImpl userDetailsService ){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)throws Exception{
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**",
            "/js/**",
            "/img/**",
            "/webjars/**",
            "/h2-console/**",
            "/",
            "/index",
            "/home",
            "/inicio",
            "/registro")
            .permitAll()

            .requestMatchers("/usuario/**").hasAnyAuthority("ADMIN")

            .anyRequest().authenticated()
        )
    
        .formLogin(login -> login
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("contraseña")
            .defaultSuccessUrl("/dasbord",true)
            .failureUrl("/login?error=true")
            .permitAll()
        )

        .logout(logout -> logout
            .logoutUrl("/logout")

            .logoutSuccessUrl("/login?logout=true")

            .invalidateHttpSession(true)

            .deleteCookies("JSESSIONID")

            .permitAll()

        )
        //.csrf().disable()
        .build();
    }
}
