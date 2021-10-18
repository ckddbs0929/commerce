package com.example.commerce.domains.member.domain;

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
@Table(name = "members")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String phone;
    @Embedded
    private Address address;

    @Builder
    public MemberEntity(String name, String phone, Address address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
