package com.example.commerce.domains.item.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemDetails {
    private Long item_id;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    public ItemDetails(ItemEntity itemEntity){
        this.item_id = itemEntity.getId();
        this.imagePath = itemEntity.getImagePath();
        this.name = itemEntity.getName();
        this.price = itemEntity.getPrice();
        this.stockQuantity = itemEntity.getStockQuantity();
    }
}
