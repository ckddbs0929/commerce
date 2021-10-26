package com.example.commerce.order;

import com.example.commerce.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int orderPrice; // 주문가격
    private int orderCount; // 주문 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int orderCount){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderCount(orderCount);
        orderItem.setOrderPrice(orderPrice);

        item.minusStock(orderCount);
        return orderItem;
    }

    // 비즈니스 로직
    public void cancel(){
        getItem().addStock(orderCount);
    }

    public int getTotalPrice(){
        return getOrderCount() * getOrderPrice();
    }
}
