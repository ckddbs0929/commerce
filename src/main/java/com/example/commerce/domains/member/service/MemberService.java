package com.example.commerce.domains.member.service;

import com.example.commerce.domains.member.domain.MemberEntity;
import com.example.commerce.domains.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Long join(MemberEntity member){
        memberRepository.save(member);
        return member.getId();
    }
}
