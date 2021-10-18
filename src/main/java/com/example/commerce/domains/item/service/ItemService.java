package com.example.commerce.domains.item.service;

import com.example.commerce.domains.item.domain.ItemDetails;
import com.example.commerce.domains.item.domain.ItemEntity;
import com.example.commerce.domains.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;

 /*   @Transactional
    public Long saveItem(AddItemRequest request){
        ItemEntity newItem = createItem(request);
        ItemEntity savedItem = itemRepository.save(newItem);

        return savedItem.getItemId();
    }

    private ItemEntity createItem(AddItemRequest request){
        return ItemEntity.builder().name(request.getName()).
                imagePath(request.getImagePath()).
                price(request.getPrice()).
                stockQuantity(request.getStockQuantity()).
                build();
    }*/

    public ItemDetails findItem(Long item_id){
        ItemEntity itemEntity = itemRepository.findById(item_id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDetails(itemEntity);
    }
}
