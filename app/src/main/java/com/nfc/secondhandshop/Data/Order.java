package com.nfc.secondhandshop.Data;

public class Order {
    private String storeName;
    private String goodName;
    private String address;
    private String phone;
    private String time;
    private String consigneeName;


    public Order() {
    }

    public Order(String storeName, String goodName, String address, String time) {
        this.storeName = storeName;
        this.goodName = goodName;
        this.address = address;
        this.time = time;
    }

    public Order(String storeName, String goodName, String address, String phone, String time, String consigneeName) {
        this.storeName = storeName;
        this.goodName = goodName;
        this.address = address;
        this.phone = phone;
        this.time = time;
        this.consigneeName = consigneeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTime() {
        return time;
    }

    public String getConsigneeName() {
        return consigneeName;
    }
}
