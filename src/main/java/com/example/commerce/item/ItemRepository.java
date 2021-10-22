package com.example.commerce.item;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Item item){
        em.persist(item);
    }
    //조회 -> primary key를 기준으로 조회!
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }


}
