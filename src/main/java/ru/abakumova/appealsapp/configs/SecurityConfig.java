package ru.abakumova.appealsapp.configs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.abakumova.appealsapp.models.enums.AccountRole;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/admin/*").hasAuthority(AccountRole.ADMIN.name())
                .antMatchers("/api/manager/*").hasAnyAuthority(AccountRole.MANAGER.name())
                .antMatchers("/api/employee/*").hasAnyAuthority(AccountRole.EMPLOYEE.name())
                .antMatchers("/api/appeal/manager/*").hasAnyAuthority(AccountRole.MANAGER.name())
                .antMatchers("/api/appeal/employee/*").hasAnyAuthority(AccountRole.EMPLOYEE.name())
                .antMatchers("/api/account/*").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
