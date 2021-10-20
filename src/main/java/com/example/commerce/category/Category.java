package com.example.commerce.category;

import com.example.commerce.item.Item;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne // 카테고리 클래스를 기준으로하면
    @JoinColumn(name = "parent_id")
    private Category parent; // 카테고리 계층구조 부모

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
