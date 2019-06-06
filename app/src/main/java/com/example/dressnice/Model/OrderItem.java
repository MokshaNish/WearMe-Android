package com.example.dressnice.Model;

import com.google.gson.annotations.SerializedName;

public class OrderItem {

    @SerializedName("id")
    private int id;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("cart")
    private Cart cart;

    @SerializedName("product")
    private Product product;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
