package com.authorization.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class ResourceServerConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //resource config
        http
                .securityMatcher("/messages/**")
                .authorizeHttpRequests()
                .requestMatchers("/messages/**").hasAuthority("SCOPE_message.read")
                .and()
//               oauth2ResourceServer() 方法,该方法将根据 application.yml 配置来配置 OAuth 服务器连接
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
