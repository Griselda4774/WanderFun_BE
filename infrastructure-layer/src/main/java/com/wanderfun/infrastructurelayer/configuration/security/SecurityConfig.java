package com.wanderfun.infrastructurelayer.configuration.security;

import com.wanderfun.domainlayer.model.users.UserRole;
import com.wanderfun.infrastructurelayer.filter.JwtFilter;
import com.wanderfun.infrastructurelayer.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails swaggerUser = User.withUsername("admin")
//                .password(passwordEncoder.encode("12345678"))
//                .roles("SWAGGER")
//                .build();
//
//        return new InMemoryUserDetailsManager(swaggerUser);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
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
                .authorizeHttpRequests(authorize -> authorize
                        // Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Auth
                        .requestMatchers("/wanderfun/auth/register").permitAll()
                        .requestMatchers("/wanderfun/auth/login").permitAll()
                        .requestMatchers("/wanderfun/auth/logout").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers("/wanderfun/auth/refresh").permitAll()
                        .requestMatchers("/wanderfun/auth/admin/register").permitAll()
                        .requestMatchers("/wanderfun/auth/otp/**").permitAll()

                        // Address
                        .requestMatchers(HttpMethod.GET, "/wanderfun/address/**").permitAll()

                        // User
                        .requestMatchers(HttpMethod.GET,"/wanderfun/user/self/**").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers("/wanderfun/user/**").hasAnyRole(UserRole.USER.name())

                        // Place
                        .requestMatchers(HttpMethod.GET, "/wanderfun/place/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/wanderfun/place").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/wanderfun/place/**").hasAnyRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/wanderfun/place/**").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/wanderfun/place/**").hasRole(UserRole.ADMIN.name())

                        // Feedback
                        .requestMatchers(HttpMethod.GET, "/wanderfun/feedback/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/wanderfun/feedback/**").hasAnyRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/wanderfun/feedback/**").hasAnyRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.DELETE, "/wanderfun/feedback/**").hasAnyRole(UserRole.USER.name())

                        // Trip
                        .requestMatchers("/wanderfun/trip/**").hasRole(UserRole.USER.name())

                        // Post
                        .requestMatchers(HttpMethod.GET,"/wanderfun/post/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/wanderfun/post/**").hasRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.PUT,"/wanderfun/post/**").hasRole(UserRole.USER.name())
                        .requestMatchers(HttpMethod.DELETE,"/wanderfun/post/**").hasRole(UserRole.USER.name())

                        // Album
                        .requestMatchers("/wanderfun/album/**").hasRole(UserRole.USER.name())

                        // Cloudinary
                        .requestMatchers("/wanderfun/cloudinary/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.USER.name())

                        // Leaderboard
                        .requestMatchers("/wanderfun/leaderboard/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/wanderfun/leaderboard/**").hasRole(UserRole.ADMIN.name())

                        // Place Category
                        .requestMatchers("/wanderfun/place/categories/**").hasRole(UserRole.ADMIN.name())

                        // Account
                        .requestMatchers("/wanderfun/account/**").hasRole(UserRole.ADMIN.name())

                        // Checkin
                        .requestMatchers("/wanderfun/checkin/**").hasRole(UserRole.USER.name())

                        // Favorite Place
                        .requestMatchers("/wanderfun/favorite-place/**").hasRole(UserRole.USER.name())

                        // Default
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());

        return http.build();
    }

}
