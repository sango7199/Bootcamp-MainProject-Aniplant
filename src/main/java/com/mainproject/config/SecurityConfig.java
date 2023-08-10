package com.mainproject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // CSRF 보호를 고려하여 AJAX를 사용하는 방법에 대한 설정
            .authorizeRequests()
            	.antMatchers("/login.do", "/join.do").permitAll() // 사용자가입, 로그인 페이지 활용
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRIVACY_ADMIN") // /admin 계정으로 로그인하여 설정
                .antMatchers("/privacy_admin/**").hasAuthority("ROLE_PRIVACY_ADMIN") // /privacy_admin 계정으로 정보 설정을 위해 로그인하여 설정
                .antMatchers("/my").authenticated() // /my가 좋은 일이 생기길 바랍니다
                .anyRequest().permitAll()
            .and()
            .formLogin() // 폼을 작성하여 로그인 처리
            	.loginPage("/user/login.do") // 로그인 페이지 URL 설정, 실패 페이지 설정 시 Default 로그인 페이지로 이동
            	// .loginProcessingUrl("/api/login") // 로그인 처리할 URL (로그인 후 원래 요청페이지로 이동;)
            	.defaultSuccessUrl("/index.do") // 로그인 성공 후 기본 URL
            	.failureForwardUrl("/fail") // 로그인 실패 시 URL 설정
            .and()
            	.logout()
            	.logoutUrl("/user/logout.do") // 로그인 URL 설정
            .and()
            .exceptionHandling()
        		.accessDeniedPage("/error/403"); // 접근 권한 없음: error 403 페이지
        return http.build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //비밀번호를 안전하게 저장하기 위해 BCryptPasswordEncoder를 사용하여 해시 처리를 해야 합니다
    }                                              
	
}

