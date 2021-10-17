package com.fa.springmobilestore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartInfo implements Serializable{

    private List<CartItemInfo> items;

    public CartInfo() {

    }

    public List<CartItemInfo> getItems() {
        return items;
    }

    public void clearCart() {
        this.items = null;
    }

    private CartItemInfo findItemByID(int proID) {
        for (CartItemInfo item : this.items) {
            if (item.getProductInfo().getProID() == proID) {
                return item;
            }
        }
        return null;
    }

    public void addProduct(ProductInfo productInfo) {
        CartItemInfo item = null;
        if (this.items != null) {
            item = this.findItemByID(productInfo.getProID());
        } else {
            this.items = new ArrayList<CartItemInfo>();
        }

        if (item == null) {
            item = new CartItemInfo();
            item.setQuantity(1);
            item.setProductInfo(productInfo);
            this.items.add(item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void removeProduct(ProductInfo productInfo) {
        CartItemInfo item = null;
        if (this.items != null) {
            item = this.findItemByID(productInfo.getProID());
        } else {
            this.items = new ArrayList<CartItemInfo>();
        }

        if (item != null) {
            this.items.remove(item);
        }
        
        if (this.items.isEmpty()) {
            this.items = null;
        }
    }

    public float getGrandTotal() {
        float grandTotal = 0;
        if (this.items != null) {
            for (CartItemInfo item : this.items) {
                grandTotal += item.getProductInfo().getPrice();
            }
        }
        return grandTotal;
    }
}
