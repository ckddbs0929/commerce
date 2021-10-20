package com.example.commerce.order;

import com.example.commerce.item.Item;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OtherItem {

    @Id @GeneratedValue
    @Column(name = "other_item_id")
    private Long id;

    private int orderPrice; // 주문가격
    private int orderCount; // 주문 수량

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
