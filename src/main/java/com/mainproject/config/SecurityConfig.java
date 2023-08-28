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
            .csrf().disable()  // CSRF protection�쓣 鍮꾪솢�꽦�솕. AJAX瑜� �궗�슜�븷 �븣 �븘�슂�븳 �꽕�젙
            .authorizeRequests()
            	.antMatchers("/login.do", "/join.do").permitAll() // �쉶�썝媛��엯, 濡쒓렇�씤 �럹�씠吏� �뿀�슜
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRIVACY_ADMIN") // /admin�� 愿�由ъ옄留� 媛��뒫�븯�룄濡� �꽕�젙
                .antMatchers("/privacy_admin/**").hasAuthority("ROLE_PRIVACY_ADMIN") // /privacy_admin�� 媛쒖씤�젙蹂� 愿�由ъ옄留� 媛��뒫�븯�룄濡� �꽕�젙
                .antMatchers("/my").authenticated() // /my�뒗 �씤利앹씠 �릺�뼱�빞 �젒�냽 媛��뒫
                .anyRequest().permitAll()
            .and()
            .formLogin() // form �쓣 �넻�븳 login �솢�꽦�솕
            	.loginPage("/user/login.do") // 濡쒓렇�씤 �럹�씠吏� URL �꽕�젙, 誘몄꽕�젙 �떆 Default 濡쒓렇�씤 �솕硫�
            	// .loginProcessingUrl("/api/login") // 濡쒓렇�씤 泥섎━�븷 URL (�솢�씤吏� 紐⑤Ⅴ寃좎쓬 �옉�룞�븞�븿;)
            	.defaultSuccessUrl("/index.do") // 濡쒓렇�씤 �꽦怨듭떆 湲곕낯 URL
            	.failureForwardUrl("/fail") // 濡쒓렇�씤 �떎�뙣 URL �꽕�젙
            .and()
            	.logout()
            	.logoutUrl("/user/logout.do") // 濡쒓렇�븘�썐 URL �꽕�젙
            .and()
            .exceptionHandling()
        		.accessDeniedPage("/error/403"); // �젒洹� 沅뚰븳 �뾾�쓬 : error 403 �럹�씠吏� 
        return http.build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 鍮꾨�踰덊샇瑜� �븞�쟾�븯寃� �븫�샇�솕�븯湲� �쐞�빐 BCryptPasswordEncoder �궗�슜
    }
	
}

