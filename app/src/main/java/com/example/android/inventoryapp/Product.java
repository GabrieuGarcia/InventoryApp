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
<<<<<<< HEAD
    private String email;

=======
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599

    public Product() {
    }

<<<<<<< HEAD
    public Product(int id, String name, Double price, byte[] image, int qtd, String email) {
=======
    public Product(int id, String name, Double price, byte[] image, int qtd) {
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.qtd = qtd;
<<<<<<< HEAD
        this.email = email;
=======
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
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
<<<<<<< HEAD

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
=======
>>>>>>> a2a2ee9702608df541b1fbb8ed82f1b0accbf599
}
