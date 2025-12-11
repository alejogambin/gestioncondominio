
package com.proyecto.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.UsuarioService;
import com.proyecto.app.service.impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
		.authorizeHttpRequests(auth -> auth
			// recursos estaticos publicos
			.requestMatchers("/css/**", "/js/**","/images/**")
			.permitAll()

			// paginas publicas
			.requestMatchers("/login", "/registro", "/index", "")
			.permitAll()

			//rutas protegidas por rol
			.requestMatchers("/dasboard/**").hasRole("ADMIN")

			//todo lo demas requiere autentificacion
			.anyRequest().authenticated()
		)
			.formLogin(login -> login 
				.loginPage("/login.html")
				.defaultSuccessUrl("/index", true)
				.permitAll()			
			)

			.logout(logout -> logout
				.logoutUrl("/logout")
				.permitAll()
			)

		.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
    @Bean
    public UserDetailsService userDEtailsService() throws Exception{
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        List<Usuario> usuarios = UsuarioServiceImpl.findAll();
        for(Usuario usuario : usuarios){
            manager.createUser(org.springframework.security.core.userdetails.User
            .withUsername(usuario.getNombre())
            .password(passwordEncoder().encode(usuario.getContraseña()))
            .roles("USER").build());
        }
        return manager;
    }
		 */
	 
}
