package com.example.Thanks_Giving_Story2.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String item_name;

    public Item() {
    }

    public Item(long id, String item_name) {
        this.id = id;
        this.item_name = item_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}

