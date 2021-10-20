package com.example.commerce;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class HelloRepositoryTest {

    @Autowired HelloRepository helloRepository;

    @Test
    @Transactional // 테스트 케이스에 있으면 테스트 끝내고 DB를 롤백함
    @Rollback(value = false)
    public void test() throws Exception{
        Mem member = new Mem();
        member.setName("chang");

        // member 파라미터에는 위의 setName으로 chang이 들어가있음
        // save 메소드는 member에 대응하는 id값이 리턴됨
        Long savedId = helloRepository.save(member);
        Mem findMember = helloRepository.find(savedId);


        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

}