package com.example.commerce.common.config;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    private String city;
    private String street;

}
