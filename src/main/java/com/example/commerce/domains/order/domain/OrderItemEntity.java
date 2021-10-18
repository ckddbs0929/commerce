package com.example.commerce.domains.order.domain;

import com.example.commerce.common.value.BaseEntity;
import com.example.commerce.domains.item.domain.ItemEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Order_items")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderItem_id")
    private Long id;

    // 주문수량
    private int orderQuantity;

    // 주문상품 가격
    private int orderItemAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Builder
    public OrderItemEntity(int orderQuantity, ItemEntity item){
        this.orderQuantity = orderQuantity;
        this.order(item, orderQuantity);
        this.calculateOrderItemTotalAmount();
    }

    // 주문이 생김과 동시에 주문상품이 필요하고, 주문이 발생하면 item의 재고량이 줄어야함
    public void order(ItemEntity item, int orderQuantity){
        item.removeStockQuantity(orderQuantity);
        this.item = item;
    }

    // 주문 총 금액
    public void calculateOrderItemTotalAmount(){
        this.orderItemAmount = this.item.getPrice() * orderQuantity;
    }

    // 비즈니스 로직
    // 주문 취소를 했을 시 위의 order 로직에서 감소한 만큼 다시 재고량을 복구하는 로직
    public void cancel(){
        this.item.addStockQuantity(this.orderQuantity);
    }
}
