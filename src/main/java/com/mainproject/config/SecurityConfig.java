package com.mainproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // CSRF protection을 비활성화. AJAX를 사용할 때 필요한 설정
            .authorizeRequests()
                .antMatchers("/login.do").permitAll()  // 로그인 페이지 허용
                .antMatchers("/join.do").permitAll()  // 회원가입 페이지 허용
                .antMatchers("/admin").hasRole("ADMIN") // /admin은 관리자만 가능하도록 설정
                .antMatchers("/privacy_admin").hasRole("PRIVACY_ADMIN") // /privacy_admin은 개인정보 관리자만 가능하도록 설정
                .antMatchers("/my").authenticated() // /my는 인증이 되어야 접속 가능
            .and()
            .formLogin() // form 을 통한 login 활성화
            .loginPage("/login.do") // 로그인 페이지 URL 설정, 미설정 시 Default 로그인 화면
            .failureForwardUrl("/fail") // 로그인 실패 URL 설정
            .and()
            .logout()
            .logoutUrl("/logout.do"); // 로그아웃 URL 설정
        return http.build();
    }
}

