package com.example.Thanks_Giving_Story2.Controller;


import com.example.Thanks_Giving_Story2.Entity.Characterroom;
import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.example.Thanks_Giving_Story2.Repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ObjectRepository objectRepository;


    @PostMapping("/room/addroom")
    public Characterroom addroom(@RequestBody Characterroom characterroom)
    {
        return this.roomRepository.save(characterroom);
    }


    @PostMapping("/movecharacter/{charID}/to/{roomID}")
    public ResponseEntity <ObjectFrame> movement(@PathVariable long charID,@PathVariable int roomID)
    {
        Optional<ObjectFrame> objectFrame = objectRepository.findById(charID);
        Optional<Characterroom> characterRoom = roomRepository.findById(objectFrame.get().getLocation());
        List roomExist = Arrays.asList(characterRoom.get().getExits());

        if(roomExist.contains(roomID)){
            objectFrame.get().setLocation(roomID);
            this.objectRepository.save(objectFrame.get());
            return new ResponseEntity<ObjectFrame>(objectFrame.get(), HttpStatus.OK);

        }
        else if (!roomExist.contains(roomID)){
            return new ResponseEntity<ObjectFrame>(objectFrame.get(),HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<ObjectFrame>(objectFrame.get(),HttpStatus.UNAUTHORIZED);
        }

    }
}
