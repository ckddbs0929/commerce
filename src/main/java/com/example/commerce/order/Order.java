package com.example.commerce.order;

import com.example.commerce.common.OrderStatus;
import com.example.commerce.delivery.Delivery;
import com.example.commerce.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // Member 테이블과 관계를 맺는 외래키
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OtherItem> list = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;
}
