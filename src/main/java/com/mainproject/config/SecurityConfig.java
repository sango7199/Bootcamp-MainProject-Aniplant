package com.mainproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            	.antMatchers("/css/**", "/js/**", "/img/**").permitAll() // CSS, JS, 이미지 파일에 대한 요청을 허용
            	.antMatchers("/login.do", "/join.do").permitAll() // 회원가입, 로그인 페이지 허용
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRIVACY_ADMIN") // /admin은 관리자만 가능하도록 설정
                .antMatchers("/privacy-admin/**").hasAuthority("ROLE_PRIVACY_ADMIN") // /privacy_admin은 개인정보 관리자만 가능하도록 설정
                .antMatchers("/mypage/**").authenticated() // /my는 인증이 되어야 접속 가능
                .antMatchers("/event/listEvents.do").authenticated() // 일정 목록은 인증이 되어야 접속 가능
                .anyRequest().permitAll() 
            .and()
            .formLogin() // ���� �ۼ��Ͽ� �α��� ó��
            	.loginPage("/user/login.do") // �α��� ������ URL ����, ���� ������ ���� �� Default �α��� �������� �̵�
            	// .loginProcessingUrl("/api/login") // �α��� ó���� URL (�α��� �� ���� ��û�������� �̵�;)
            	.defaultSuccessUrl("/index.do") // �α��� ���� �� �⺻ URL
            	.failureForwardUrl("/fail") // �α��� ���� �� URL ����
            .and() 
            	.logout()
            	.logoutUrl("/user/logout.do") // 로그아웃 URL 설정
            	.logoutSuccessUrl("/index.do") // 로그아웃 성공 시 이동할 URL 지정
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            .and()
            .sessionManagement()
                .invalidSessionUrl("/session-expired") // 세션 만료시
            .and()
            .exceptionHandling()
        		.accessDeniedPage("/error/403"); // ���� ���� ����: error 403 ������
        return http.build();
    }


	@Bean
<<<<<<< HEAD
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //��й�ȣ�� �����ϰ� �����ϱ� ���� BCryptPasswordEncoder�� ����Ͽ� �ؽ� ó���� �ؾ� �մϴ�
    }   
	
	
	
	

	
	
	
	
	
}
=======
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 비밀번호를 안전하게 암호화하기 위해 BCryptPasswordEncoder 사용
	}
>>>>>>> 1f620c6524e6f2b12f0acbff33e1b430d6f406dd


}
