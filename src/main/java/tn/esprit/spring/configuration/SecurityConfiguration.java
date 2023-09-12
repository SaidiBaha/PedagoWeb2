package tn.esprit.spring.configuration;


import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static tn.esprit.spring.entity.Permission.*;
import static tn.esprit.spring.entity.Role.ADMIN;
import static tn.esprit.spring.entity.Role.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfiguration {


    private final Filter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**",
                        "/users/**",
                        "/thematiques/**",
                        "/competences/**",
                        "/sous-competences/**",
                        "/questions/**",
                        "/options/**",
                        "/api/v1/user/**"
                        )
                .permitAll()


               /* .requestMatchers("/users/**"
                        , "/questions/**"
                        ,"/options/**"
                        ,"/thematiques/**"
                        ,"/competences/**"
                        ,"/sous-competences/**"



                ).hasRole(ADMIN.name())

                .requestMatchers(GET, "/users/**"
                        ,"/thematiques/**"
                        ,"/competences/**"
                        ,"/sous-competences/**"
                        ,"/questions/**"
                        ,"/options/**"

                ).hasAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/users/**"
                        ,"/thematiques/**"
                        , "/competences/**"
                        ,"/sous-competences/**"
                        ,"/questions/**"
                        ,"/options/**"

                ).hasAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "/users/**"
                        ,"/thematiques/**"
                        , "/competences/**"
                        ,"/sous-competences/**"
                        ,"/questions/**"
                        ,"/options/**"

                ).hasAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "/users/**"
                        ,"/thematiques/**"
                        , "/competences/**"
                        ,"/sous-competences/**"
                        ,"/questions/**"
                        ,"/options/**"

                ).hasAuthority(ADMIN_DELETE.name())



                .requestMatchers("/api/v1/user/**").hasRole(USER.name())

                .requestMatchers(GET, "/api/v1/user/**").hasAuthority(USER_READ.name())*/



                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
