package com.learncode.DockerTest_10_2026.controller;

import com.learncode.DockerTest_10_2026.entity.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
    private final List<Item> iList=new ArrayList<Item>();
    public ItemController(){

        iList.add(new Item(1,"Laptop",100L));
        iList.add(new Item(2,"Desktop",300L));
        iList.add(new Item(3,"Mouse",50L));
        iList.add(new Item(4,"Cycle",15L));

    }





    @GetMapping("/hi")
    public String Hi() {

        return "Hi All..";
    }


    @GetMapping("/allItem")
    public List<Item> findAllList() {

        return iList;
    }

    @GetMapping("/{id}")
    public Item findItemById(@PathVariable Integer id) {
        return iList.stream().filter(z -> z.getId() == id).findFirst().get();

    }

    @PostMapping("/addIteam")
    public Item createProduct(@RequestBody Item item) {
        iList.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable int id, @RequestBody Item item) {
        Item itemToBeUpdated = iList.stream().filter(c -> c.getId() == id).findFirst().get();
        itemToBeUpdated.setItemName(item.getItemName());
        itemToBeUpdated.setItemPrice(item.getItemPrice());
        iList.add(itemToBeUpdated);
        return new ResponseEntity<>(itemToBeUpdated, HttpStatus.OK);
    }


//@PutMapping("/{id}")
//public Item update(@PathVariable int id, @RequestBody Item item) {
//    System.out.println("HiList....."+iList);
//    return iList.stream()
//            .filter(c -> c.getId() == id)
//            .peek(c -> {
//                c.setItemName(item.getItemName());
//                c.setItemPrice(item.getItemPrice());
//            }).findAny().get();
//
//}

    @DeleteMapping("/{id}")
    public boolean deleteItem(int id) {
        Optional<Item> itemOptional = iList.stream().filter(z -> z.getId() == id).findFirst();
        if (itemOptional.isPresent()) {
            iList.remove(itemOptional.get());
            return true;
        }
        return false;
    }

}
