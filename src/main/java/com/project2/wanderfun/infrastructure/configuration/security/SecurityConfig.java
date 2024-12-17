package com.project2.wanderfun.infrastructure.configuration.security;

import com.project2.wanderfun.domain.model.enums.UserRole;
import com.project2.wanderfun.infrastructure.filter.JwtFilter;
import com.project2.wanderfun.infrastructure.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter, UserDetailServiceImpl userDetailService) {
        this.jwtFilter = jwtFilter;
        this.userDetailService = userDetailService;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails swaggerUser = User.withUsername("admin")
                .password(passwordEncoder.encode("12345678"))
                .roles("SWAGGER")
                .build();

        return new InMemoryUserDetailsManager(swaggerUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailServiceImpl userDetailService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Swagger
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                                // Auth
                                .requestMatchers("/wanderfun/auth/register").permitAll()
                                .requestMatchers("/wanderfun/auth/login").permitAll()
                                .requestMatchers("/wanderfun/auth/logout").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                                .requestMatchers("/wanderfun/auth/refresh").permitAll()
                                .requestMatchers("/wanderfun/auth/admin/register").permitAll()
                                // User
                                .requestMatchers(HttpMethod.GET,"/wanderfun/user/self/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                                .requestMatchers("/wanderfun/user/**").hasAnyRole(UserRole.ADMIN.name())

                                // Place
                                .requestMatchers(HttpMethod.GET,"/wanderfun/place/**").permitAll()
                                .requestMatchers("/wanderfun/place/**").hasAnyRole(UserRole.ADMIN.name())

                                // Trip
                                .requestMatchers("/wanderfun/trip/**").hasAnyRole(UserRole.USER.name())

                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());
        return http.build();
    }
}
