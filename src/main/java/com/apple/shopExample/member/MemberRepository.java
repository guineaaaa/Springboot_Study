package com.apple.shopExample.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Derived query Methods (and/or 조건 주기, 특정 문자 포함되었는지 검색하기, 숫자 이상 이하, 정렬하기 등)
    Optional<Member> findByUsername(String username);
}
