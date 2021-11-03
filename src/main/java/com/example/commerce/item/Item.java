package com.example.commerce.item;

import com.example.commerce.category.Category;
import com.example.commerce.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dType")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    // 비즈니스 로직 //

    // stock 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    // stock 감소
    public void minusStock(int quantity){
        int restQuantity = this.stockQuantity - quantity;
        if(restQuantity<0){
            throw new NotEnoughStockException("재고가 필요합니다.");
        }
        this.stockQuantity = restQuantity;
    }
}
