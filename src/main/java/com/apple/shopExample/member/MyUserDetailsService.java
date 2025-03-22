package com.apple.shopExample.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // DB에서 username을 가진 유저를 가져와
        // return new User(유저아이디, 비번, 권한) 해주세요
        // spring 시큐리티가 내부적으로 유저가 제출한 비번==DB에있던 비번을 알아서해주는데 DB어디에있는지는 스프링시큐리티가 모르니까 그 기능을 만드는거임
        var result=memberRepository.findByUsername(username);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("그런 아이디 없음");
        }

        var user=result.get();

        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        var a=new CustomUser(user.getUsername(), user.getPassword(), authorities);
        a.displayName=user.getDisplayname();
        return a;
    }
}

class CustomUser extends User{
    public String displayName;

    public CustomUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
