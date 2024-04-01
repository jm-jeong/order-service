package com.inflearn.orderservice.service;

import com.inflearn.orderservice.domain.Member;
import com.inflearn.orderservice.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName(value = "회원가입")
    @Test
    void join() {
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @DisplayName("중복 회원 예외")
    @Test
    void findDuplicateMember() {

        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야 함");

    }


}