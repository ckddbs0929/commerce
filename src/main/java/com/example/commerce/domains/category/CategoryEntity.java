package com.example.commerce.domains.category;

import com.example.commerce.common.value.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    private Long parentId;

    @Builder
    public CategoryEntity(String categoryName, Long parentId){
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}