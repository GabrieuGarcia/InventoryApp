package com.example.android.inventoryapp;

import java.io.Serializable;

/**
 * Created by lucas on 30/05/18.
 */

public class Product implements Serializable {
    private int id;
    private String name;
    private Double price;
    private byte[] image;
    private int qtd;

    public Product() {
    }

    public Product(int id, String name, Double price, byte[] image, int qtd) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
