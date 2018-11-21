package com.example.Thanks_Giving_Story2.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Object_registry {
    @Id
    private String name;
    private String objectfrm;

    public Object_registry() {
    }

    public Object_registry(String name, String item, String objectfrm) {
        this.name = name;
        this.objectfrm = objectfrm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getObjectfrm() {
        return objectfrm;
    }

    public void setObjectfrm(String objectfrm) {
        this.objectfrm = objectfrm;
    }
}


