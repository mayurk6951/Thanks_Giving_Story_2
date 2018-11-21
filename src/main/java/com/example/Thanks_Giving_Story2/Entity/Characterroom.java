package com.example.Thanks_Giving_Story2.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Characterroom {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;
    private String descript;
    private int[] exits;

    public Characterroom() {
    }

    public Characterroom(long roomid, String name, String desc, int[] exits) {
        this.id = roomid;
        this.name = name;
        this.descript = desc;
        this.exits = exits;
    }

    public long getRoomid() {
        return id;
    }

    public void setRoomid(long roomid) {
        this.id = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int[] getExits() {
        return exits;
    }

    public void setExits(int[] exits) {
        this.exits = exits;
    }
}
