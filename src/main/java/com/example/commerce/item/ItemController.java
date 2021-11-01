package com.example.commerce.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;


@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());


        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping(value = "/items")
    public String findList(Model model){
        List<Item> item = itemService.findAllItem();
        model.addAttribute("items", item);
        return "items/itemList";
    }

    @GetMapping(value = "/items/{id}/edit")
    public String updateItemForm(@PathVariable(name = "id") Long id, Model model){

        Book form = (Book)itemService.findOneItem(id);

/*
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
*/
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping(value = "/items/{id}/edit")
    public String updateItem(@PathVariable Long id ,@ModelAttribute("form") BookForm form){

/*        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);*/

        itemService.updateItem(id, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
