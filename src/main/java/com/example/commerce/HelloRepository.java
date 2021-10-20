package com.example.commerce;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class HelloRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Mem member){
        em.persist(member);
        return member.getId();
    }

    public Mem find(Long id){
        return em.find(Mem.class, id);
    }

}
