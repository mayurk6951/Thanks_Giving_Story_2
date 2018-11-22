package com.example.Thanks_Giving_Story2.Controller;

import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Repository.ItemRepository;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.example.Thanks_Giving_Story2.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Random;


@RestController
public class BattleController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ObjectRepository objectRepository;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/battle/{characterId1}/{characterId2}")
    public ResponseEntity<String> battle (@PathVariable long characterId1 , @PathVariable long characterId2)
    {

        Optional<ObjectFrame> objectFrame1= this.objectRepository.findById(characterId1);
        Optional<ObjectFrame> objectFrame2= this.objectRepository.findById(characterId2);

        Random random = new Random();
        String output = "";
        int object1 = random.nextInt(21);
        int object2 = random.nextInt(21);
        int blow = random.nextInt(9);
        if(object1>object2)
        {
           objectFrame2.get().setHitpoints(objectFrame2.get().getHitpoints()-blow);
           if(objectFrame2.get().getHitpoints()>1) {
               output = objectFrame1.get().getName() + " successfully strikes " + objectFrame2.get().getName() + " and does " + blow + " damage.";
           }
           else{
               output = objectFrame1.get().getName() + " successfully strikes " + objectFrame2.get().getName() + " and does " + blow + " damage."+  objectFrame2.get().getName() + " is killed.";
           }
            this.objectRepository.save(objectFrame2.get());
        }
        else  if(object2>object1)
        {
            objectFrame1.get().setHitpoints(objectFrame1.get().getHitpoints()-blow);
            if(objectFrame1.get().getHitpoints()>1) {
                output = objectFrame2.get().getName() + " successfully strikes " + objectFrame1.get().getName() + " and does " + blow + " damage.";
            }
            else{
                output = objectFrame2.get().getName() + " successfully strikes " + objectFrame1.get().getName() + " and does " + blow + " damage."+  objectFrame1.get().getName() + " is killed.";
            }
            this.objectRepository.save(objectFrame1.get());
        }
        else{
                output = objectFrame1.get().getName() + " and " + objectFrame1.get().getName() + " attack each other but both fail to land a blow. " ;
        }

        return new ResponseEntity<String>(output, HttpStatus.OK);


    }

}
