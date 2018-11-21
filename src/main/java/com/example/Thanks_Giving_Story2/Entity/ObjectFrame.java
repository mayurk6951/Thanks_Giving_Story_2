package com.example.Thanks_Giving_Story2.Entity;




import org.springframework.data.util.Pair;

import javax.persistence.*;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity ()
public class ObjectFrame {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;



    private String name;
    private String Klass;
    private long obj_int;
    private long wis;
    private long cha;
    private long str;
    private long dex;
    private long con;
    private long location;
    @ElementCollection
    private Map<Long,String> inventory;
    private long hitpoints;



    public ObjectFrame(){};

    public ObjectFrame(long id, String name, String klass, long obj_int, long wis, long cha, long str, long dex, long con, long location, Map<Long, String> inventory, long hitpoints) {
        this.id = id;
        this.name = name;
        Klass = klass;
        this.obj_int = obj_int;
        this.wis = wis;
        this.cha = cha;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.location = location;
        this.inventory = inventory;
        this.hitpoints = hitpoints;
    }

    public Map<Long, String> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Long, String> inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlass() {
        return Klass;
    }

    public void setKlass(String klass) {
        Klass = klass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getObj_int() {
        return obj_int;
    }

    public void setObj_int(long obj_int) {
        this.obj_int = obj_int;
    }

    public long getWis() {
        return wis;
    }

    public void setWis(long wis) {
        this.wis = wis;
    }

    public long getCha() {
        return cha;
    }

    public void setCha(long cha) {
        this.cha = cha;
    }

    public long getStr() {
        return str;
    }

    public void setStr(long str) {
        this.str = str;
    }

    public long getDex() {
        return dex;
    }

    public void setDex(long dex) {
        this.dex = dex;
    }

    public long getCon() {
        return con;
    }

    public void setCon(long con) {
        this.con = con;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    public long getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(long hitpoints) {
        this.hitpoints = hitpoints;
    }
}
