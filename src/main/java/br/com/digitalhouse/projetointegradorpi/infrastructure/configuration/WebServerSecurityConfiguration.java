package br.com.digitalhouse.projetointegradorpi.infrastructure.configuration;

import br.com.digitalhouse.projetointegradorpi.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebServerSecurityConfiguration {
    private final JwtRequestFilter jwtAuthenticationFilter;
    private final UsuarioService usuarioService;


    @Bean
    //Criptografa senha no banco de dados
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //permite apenas requisições para authentication, bloqueia todas as outras requisições
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                            request.requestMatchers(
                                            antMatcher(HttpMethod.POST, "/usuarios/**"),
                                            antMatcher(HttpMethod.GET, "/carros/**"),
                                            antMatcher(HttpMethod.GET, "/categorias/**"),
                                            antMatcher(HttpMethod.GET, "/cidades/**"),
                                            antMatcher(HttpMethod.GET, "/caracteristicas/**"),
                                            antMatcher("/authentication/**"),
                                            antMatcher("/actuator/**"),
                                            antMatcher("/swagger-ui/**"),
                                            antMatcher("/proxy/**"),
                                            antMatcher("/v3/api-docs/**"))
                                    .permitAll();
                            request.anyRequest().authenticated();
                        }

                )

                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    //Adiciona um provedor de autenticação com a senha encriptada
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
