package com.example.commerce.member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Member member){
        em.persist(member);
    }
    //조회 -> primary key를 기준으로 조회!
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    //전체(리스트) 조회
    public List<Member> findAll(){
/*        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;*/
        // FROM 절의 Member m 에서 m 은 Alias이다.
        // JPQL은 테이블을 기준으로 SQL을 사용하는것이 아닌 객체를 기준으로 사용 그래서 FROM절에 Member가 들어감
       return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    // 이름으로 조회
    public List<Member> findByName(String name){
        /*List<Member> nameResult = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return nameResult;*/
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Member> findBypass(String password){
        return em.createQuery("select m from Member m where m.password = :password", Member.class)
                .setParameter("password", password)
                .getResultList();
    }
}
