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
            .csrf().disable()  // CSRF ��ȣ�� �����Ͽ� AJAX�� ����ϴ� ����� ���� ����
            .authorizeRequests()
            	.antMatchers("/login.do", "/join.do").permitAll() // 회원가입, 로그인 페이지 허용
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRIVACY_ADMIN") // /admin은 관리자만 가능하도록 설정
                .antMatchers("/privacy_admin/**").hasAuthority("ROLE_PRIVACY_ADMIN") // /privacy_admin은 개인정보 관리자만 가능하도록 설정
                .antMatchers("/mypage/**").authenticated() // /my는 인증이 되어야 접속 가능
                .anyRequest().permitAll()
            .and()
            .formLogin() // ���� �ۼ��Ͽ� �α��� ó��
            	.loginPage("/user/login.do") // �α��� ������ URL ����, ���� ������ ���� �� Default �α��� �������� �̵�
            	// .loginProcessingUrl("/api/login") // �α��� ó���� URL (�α��� �� ���� ��û�������� �̵�;)
            	.defaultSuccessUrl("/index.do") // �α��� ���� �� �⺻ URL
            	.failureForwardUrl("/fail") // �α��� ���� �� URL ����
            .and()
            	.logout()
            	.logoutUrl("/user/logout.do") // �α��� URL ����
            .and()
            .exceptionHandling()
        		.accessDeniedPage("/error/403"); // ���� ���� ����: error 403 ������
        return http.build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //��й�ȣ�� �����ϰ� �����ϱ� ���� BCryptPasswordEncoder�� ����Ͽ� �ؽ� ó���� �ؾ� �մϴ�
    }                                              
	
}

