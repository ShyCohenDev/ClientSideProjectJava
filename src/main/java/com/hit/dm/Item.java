package com.hit.dm;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Item implements Serializable {
    private final String id;
    private String name;
    private String description;
    private float price;
    private int countRemaining;
    private List<Item> cart;

    public Item(String name, String description, float price, int countRemaining) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.countRemaining = countRemaining;
    }

    public Item(String id, String name, String description, float price, int countRemaining) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.countRemaining = countRemaining;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCountRemaining() {
        return countRemaining;
    }

    public void setCountRemaining(int countRemaining) {
        this.countRemaining = countRemaining;
    }

    @Override
    public String toString() {
        return this.name.replace('-', ' ') + "\n" + this.description.replace('-', ' ') + "\nprice: " + this.price + "₪, " + " remaining: " + this.countRemaining;
    }
}
