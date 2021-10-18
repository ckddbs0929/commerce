package com.example.commerce.domains.order.domain;

import com.example.commerce.common.value.BaseEntity;
import com.example.commerce.domains.delivery.domain.DeliveryEntity;
import com.example.commerce.domains.delivery.domain.DeliveryStatus;
import com.example.commerce.domains.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    // 주문 수량
    private int totalAmount;

    // 주문 상태
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    // 주문 삭제 여부
    private boolean removed;
    // 주문 삭제 시간
    private LocalDateTime removedTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity orderer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity deliveryInfo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderItem_id")
    private List<OrderItemEntity> orderItemList = new ArrayList<>();

    @Builder
    public OrderEntity(MemberEntity orderer, DeliveryEntity deliveryInfo, List<OrderItemEntity> orderItemList){
        this.orderer = orderer;
        this.deliveryInfo = deliveryInfo;
        this.setOrderItemList(orderItemList);
        this.orderStatus = OrderStatus.ORDERED_STATUS;
    }

    private void setOrderItemList(List<OrderItemEntity> orderItemList){
       orderItemList.stream().forEach(orderItemEntity ->
                this.orderItemList.add(orderItemEntity));
        this.calculateTotalAmount();
    }

    private void calculateTotalAmount(){
        this.totalAmount = this.orderItemList.stream().mapToInt(orderItem ->
                orderItem.getOrderItemAmount()).sum();
    }

    // 비즈니스 로직
    public void cancel(){
        if(this.deliveryInfo.getDeliveryStatus() == DeliveryStatus.COMPLETE_STATUS ||
        this.deliveryInfo.getDeliveryStatus() == DeliveryStatus.SHIPPING_STATUS)
            throw new IllegalStateException("이미 배송중이거나 배송이 완료된 주문은 취소가 불가합니다.");

        this.orderItemList.stream().forEach(orderItem -> orderItem.cancel());

        this.orderStatus = OrderStatus.CANCEL_STATUS;
    }
}
