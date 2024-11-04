//package com.silence.config;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @author silence
// * @since 2024/9/12 11:44
// **/
//@Configuration(proxyBeanMethods = false)
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http.authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//                        .requestMatchers("/ott/sent").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());
////                .oneTimeTokenLogin(Customizer.withDefaults());
//        // @formatter:on
//        return http.build();
//    }
//
//    @Bean
//    InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}
