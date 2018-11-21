package com.example.Thanks_Giving_Story2.Controller;

import com.example.Thanks_Giving_Story2.Entity.ObjectFrame;
import com.example.Thanks_Giving_Story2.Entity.Object_registry;
import com.example.Thanks_Giving_Story2.Repository.ObjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ObjectController {

    List<Integer> randomvalues = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    public ObjectController(){
        Random random_generator = new Random();

        for(int i =0;i<=5;i++){
            int n = random_generator.nextInt(18)+8;
            randomvalues.add(n);
        }
        Collections.sort(randomvalues);
    }

    @Autowired
    ObjectRepository repo;

    @GetMapping("/object/generate/{name}/{Klass}")
    public ResponseEntity<ObjectFrame> getobject(@PathVariable String name, @PathVariable String Klass) throws JsonProcessingException {
        Object_registry objreg= new Object_registry();
        objreg.setName(Klass);
        ObjectFrame obj = new ObjectFrame();
        obj.setKlass(Klass);
        obj.setName(name);
        obj.setInventory(new String[0]);
        obj.setCon(this.randomvalues.get(1));
        obj.setHitpoints(2*obj.getCon());
        obj.setLocation(4);
        obj.setWis(randomvalues.get(2));
        HttpStatus status;
        if(Klass.equals("Warrior")){

            obj.setStr(randomvalues.get(5));
            obj.setObj_int(randomvalues.get(0));
            obj.setCha(randomvalues.get(4));
            obj.setDex(randomvalues.get(3));
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            objreg.setObjectfrm(json);
            status = HttpStatus.OK;
        }
        else if(Klass.equals("Archer"))
        {
            obj.setStr(randomvalues.get(3));
            obj.setObj_int(randomvalues.get(4));
            obj.setCha(randomvalues.get(0));
            obj.setDex(randomvalues.get(5));
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            objreg.setObjectfrm(json);
            status = HttpStatus.OK;
        }
        else if(Klass.equals("Wizard"))
        {
            obj.setStr(randomvalues.get(0));
            obj.setObj_int(randomvalues.get(5));
            obj.setCha(randomvalues.get(3));
            obj.setDex(randomvalues.get(4));
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            objreg.setObjectfrm(json);
            status = HttpStatus.OK;
        }
        else if(Klass.equals("Rogue"))
        {
            obj.setStr(randomvalues.get(0));
            obj.setObj_int(randomvalues.get(3));
            obj.setCha(randomvalues.get(5));
            obj.setDex(randomvalues.get(4));
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            objreg.setObjectfrm(json);
            status = HttpStatus.OK;
        }
        else {
            obj.setKlass("Not Found");
            obj.setName("Error 404");
            status=HttpStatus.NOT_FOUND;
        }
          this.repo.save(obj);
        return new ResponseEntity<ObjectFrame>(obj,status);
    }
    @PostMapping("/object/setchar")
    public ObjectFrame setchar(@RequestBody ObjectFrame objectFrame)
    {
    return this.repo.save(objectFrame);
    }

    @GetMapping("/object/get/allObject")
    public Iterable<ObjectFrame> getAllObjects(){return this.repo.findAll();}

    @GetMapping("/test")
    public String getHello() {
        return "hello";
    }

}
