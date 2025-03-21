package com.apple.shopExample;

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

    // spring이 뽑은 object를 bean이라고 한다
    // bean으로 만들고싶은 object가 있으면 그걸 뱉는 함수를 만들고 @Configuration
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 어떤 페이지를 로그인 검사할지 
    // filterChain: 모든 유저의 요청과 서버의 응답 사이에 자동으로 실행해주고 싶은 코드를 담는곳
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf -> 다른 사이트에서 우리 url로 보낼 상황 대비
        // csrf 공격을 잠시 꺼줌..
        // jwt를 쓸 경우 fetch()의 headers:{}에 넣어서 보내면 csrf 예방 가능이긴함
        http.csrf((csrf)->csrf.disable());

        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
                // 특정 url에 로그인 검사를 할지 말지
                // /** -> 모든 url
        );

        http.formLogin((formLogin)
                ->formLogin.loginPage("/login")
                .defaultSuccessUrl("/list", true)
                .failureUrl("/fail")
        );


        return http.build();
    }
}