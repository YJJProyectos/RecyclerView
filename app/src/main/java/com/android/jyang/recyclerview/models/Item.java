package com.android.jyang.recyclerview.models;

/**
 * Created by jyang on 3/4/2018.
 */

public class Item {

    public String name;
    public int poster;

    public Item() {

    }

    public Item(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public int getPoster() {
        return poster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
