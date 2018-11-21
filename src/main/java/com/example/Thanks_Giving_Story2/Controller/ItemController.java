package com.example.Thanks_Giving_Story2.Controller;

import com.example.Thanks_Giving_Story2.Entity.Item;
import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Repository.ItemRepository;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.example.Thanks_Giving_Story2.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ItemController {

        @Autowired
        RoomRepository roomRepository;

        @Autowired
        ObjectRepository objectRepository;

        @Autowired
        ItemRepository itemRepository;

    @PostMapping("/item/add")
    public Item addItem(@RequestBody Item item)
    {
        return this.itemRepository.save(item);
    }

    @PostMapping("/inventory/pickup/{characterId}/{itemId}")
    public ResponseEntity <ObjectFrame> giveitem (@PathVariable long characterId , @PathVariable long itemId)
    {

        Optional<ObjectFrame> objectFrame= this.objectRepository.findById(characterId);
        Map<Long,String> item = objectFrame.get().getInventory();
        item.put(itemId,itemRepository.findById(itemId).get().getItem_name());

        objectFrame.get().setInventory(item);

        this.objectRepository.save(objectFrame.get());

        return new ResponseEntity<ObjectFrame>(objectFrame.get(),HttpStatus.OK);
    }

    @PostMapping("/inventory/drop/{characterId}/{itemId}")
    public ResponseEntity <ObjectFrame> dropitem (@PathVariable long characterId , @PathVariable long itemId)
    {
//        Map<Long,String> item = new HashMap<>();
//        item.(itemId,itemRepository.findById(itemId).get().getItem_name());

        ObjectFrame obj = new ObjectFrame();
        Optional<ObjectFrame> objectFrame= this.objectRepository.findById(characterId);

        Map<Long,String> item = objectFrame.get().getInventory();
        item.remove(itemId);
        objectFrame.get().setInventory(item);
        this.objectRepository.save(objectFrame.get());

        return new ResponseEntity<ObjectFrame>(objectFrame.get(),HttpStatus.OK);
    }

}
