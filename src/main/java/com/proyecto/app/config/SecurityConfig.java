package com.proyecto.app.config;

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
        http.authorizeHttpRequests(auth -> auth
            // Permitir rutas públicas sin autenticación
            .requestMatchers("/","/index","/home","/inicio").permitAll()
            .requestMatchers("/registro").permitAll()
            .requestMatchers("/contacto","/nosotros","/usuario/json","/error").permitAll()
            // Permitir recursos estáticos sin autenticación
            .requestMatchers("/css/**","/js/**","/img/**","/images/**","/webjars/**").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            // Proteger rutas administrativas
            .requestMatchers("/torre/**").hasAuthority("ADMIN")
            .requestMatchers("/usuario/delete/**").hasAuthority("ADMIN")
            .requestMatchers("/usuario/editar/**").hasAuthority("ADMIN")
            // Todas las demás rutas requieren autenticación
            .anyRequest().authenticated()
        );
        
        http.formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/dasboard", true)
            .failureUrl("/login?error=true")
            .permitAll()
        );
        
        http.logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );
        
        //http.csrf().disable();
        http.headers(headers -> headers.frameOptions().disable());
        
        return http.build();
    }
}
