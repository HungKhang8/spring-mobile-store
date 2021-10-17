package com.fa.springmobilestore.model;

import java.io.Serializable;

public class CartItemInfo implements Serializable{

    private ProductInfo productInfo;
    private int quantity;

    public CartItemInfo() {
        this.quantity = 0;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public float getAmount() {
        return this.productInfo.getPrice() * this.quantity;
    }

}
