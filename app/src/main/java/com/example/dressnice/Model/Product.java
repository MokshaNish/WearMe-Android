package com.example.dressnice.Model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("pid")
    private int pid;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("imageUrl")
    private String imageUrl;

    public Product(int pid, String name, double price, int quantity, String imageUrl) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return pid;
    }

    public void setId(int id) {
        this.pid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
