package com.fa.springmobilestore.model;

import com.fa.springmobilestore.entity.Product;
import java.io.Serializable;

public class ProductInfo implements Serializable{

    private int proID;
    private String proName;
    private String description;
    private float price;
    private int stock;
    private String manufacturer;
    private String category;
    private int condition;
    private String imgLink;

    public ProductInfo() {
    }

    public ProductInfo(Product product) {
        this.proID = product.getProID();
        this.proName = product.getProName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.manufacturer = product.getManufacturer();
        this.category = product.getCategory();
        this.condition = product.getCondition();
        this.imgLink = product.getImgLink();
    }

    /* For JPA/Hibernate Query */
    public ProductInfo(int proID, String proName, String description, float price, int stock, String manufacturer, String category, int condition, String imgLink) {
        this.proID = proID;
        this.proName = proName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.manufacturer = manufacturer;
        this.category = category;
        this.condition = condition;
        this.imgLink = imgLink;
    }

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

}
