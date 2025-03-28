package com.apple.shopExample.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 입출력하고 싶은 곳에 DI
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    String register(Authentication auth){
        if(auth.isAuthenticated()){
            return "redirect:/list";
        }
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

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/mypage")
    public String mypage(Authentication auth){
        CustomUser result=(CustomUser)auth.getPrincipal();
        System.out.println("디스플레이네임"+result.displayName);

        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_USER")
        ));
        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser(){
        var a=memberRepository.findById(1L);
        var result=a.get();
        var data=new MemberDto(result.getUsername(), result.getDisplayname(), result.getId());
        data.id=1L;
        return data;
    }
}

// DTO object를 다른 형식으로 변환해서 보내고 싶을때 사용
// Object를 변환해서 전송하려면 Map또는 DTO 클래스 사용
// 1. DTO 쓰면 보내는 데이터의 타입 체크가 쉬움
// 2. 클래스로 만들어 두게 되면 재사용 가능
class MemberDto{
    public String username; //public 또는 @Getter가 붙어있어야 json변환 가능
    public String displayName;
    public Long id;

    MemberDto(String a, String b){
        this.username=a;
        this.displayName=b;
    }
    MemberDto(String a, String b, Long id){
        this.username=a;
        this.displayName=b;
        this.id=id;
    }
}