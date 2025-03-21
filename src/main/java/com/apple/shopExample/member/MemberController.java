package com.apple.shopExample.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 입출력하고 싶은 곳에 DI
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String register(){
        return "register.html";
    }

    @PostMapping("/member")
    String addMember(String username, String password, String displayName){
        Member member=new Member();
        member.setUsername(username);

        // 해싱해서 비밀번호 저장
        // object를 스프링이 뽑게하고 DI로 넣어도 된다.
        // 남이 만든 클래스의 object를 DI로 쓰고 싶으면 @Bean을 써보자
        var hash=passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayname(displayName);

        // 유저가 보낸 아이디, 비번, 이름 DB에 저장
        memberRepository.save(member);
        return "redirect:/list";
    }
}
