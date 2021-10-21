package com.example.commerce.delivery;

import com.example.commerce.common.Address;
import com.example.commerce.common.DeliveryStatus;
import com.example.commerce.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //
    private DeliveryStatus status;


}
