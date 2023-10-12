package dev.brunocesar.imovelsimplificado.advertise.configs;

import dev.brunocesar.imovelsimplificado.advertise.configs.security.JwtAuthenticationEntryPoint;
import dev.brunocesar.imovelsimplificado.advertise.configs.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] PUBLIC_MATCHERS;

    private final SecurityFilter securityFilter;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    static {
        PUBLIC_MATCHERS = new String[]{"/health", "/swagger-ui/**", "/v3/api-docs/**"};
    }

    public WebSecurityConfig(SecurityFilter securityFilter,
                             JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.securityFilter = securityFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(FormLoginConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);

        httpSecurity.sessionManagement(configure -> configure.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.exceptionHandling(configure -> configure.authenticationEntryPoint(unauthorizedHandler));

        httpSecurity.authorizeHttpRequests(configure -> configure
                .requestMatchers(PUBLIC_MATCHERS).permitAll()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.POST, "/advertise", "/login").permitAll()
                .anyRequest().authenticated());

        return httpSecurity.build();
    }
}