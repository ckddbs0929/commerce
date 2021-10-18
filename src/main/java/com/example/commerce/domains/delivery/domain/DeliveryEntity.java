package com.example.commerce.domains.delivery.domain;

import com.example.commerce.common.config.Address;
import com.example.commerce.common.value.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "deliveries")
public class DeliveryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public DeliveryEntity(Address address){
        this.address = address;
        this.deliveryStatus = DeliveryStatus.READY_STATUS;
    }
}
