package com.example.commerce.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("KIM CHUL SU");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복회원확인() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName("KIM1");

        Member member2 = new Member();
        member2.setName("KIM1");
        // when
        memberService.join(member1);
        try {
            memberService.join(member2); // 예외발생지점
        } catch (IllegalStateException e){
            return;
        }
        // then
        fail("예외 발생 되야함");
    }
}