package com.example.commerce.domains.item.domain;


import com.example.commerce.common.value.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;

    private String imagePath;
    // 상품 이름
    private String name;
    // 상품 가격
    private int price;
    // 재고량
    private int stockQuantity;

    @Builder
    public ItemEntity(String imagePath, String name, int price, int stockQuantity){
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // 비즈니스 로직
    public void removeStockQuantity(int orderQuantity){
        int restStockQuantity = this.stockQuantity - orderQuantity;

        if(restStockQuantity<0)
            throw new IllegalStateException("재고가 없습니다.");

        this.stockQuantity = restStockQuantity;
    }

    public void addStockQuantity(int quantity){
        this.stockQuantity += quantity;
    }
}
