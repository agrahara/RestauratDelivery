package com.restaurant.online.entity;

public class Product {

    int itemId;

    String productName;

    public Product(int itemId, String productName) {
        this.itemId = itemId;
        this.productName = productName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product()
    {

    }
}
