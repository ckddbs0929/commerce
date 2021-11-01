package com.example.commerce.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findOneItem(Long itemId){
       return itemRepository.findOne(itemId);
    }

    public List<Item> findAllItem(){
       return itemRepository.findAll();
    }

    @Transactional
    public Item updateItem(Long itemId, String name, int price, int stockQuantity){

        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        return findItem;
    }

}
