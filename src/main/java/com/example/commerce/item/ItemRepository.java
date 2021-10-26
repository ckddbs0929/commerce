package com.example.commerce.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    //저장
    public void save(Item item){
        if(item.getId()==null){
            em.persist(item);
        }
        else{
            em.merge(item); // 업데이트
        }
    }

    //조회 -> primary key를 기준으로 조회!
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
