package com.nfc.secondhandshop.Data;

public class Good {
    private String goodName;
    private String storeName;
    private String goodIntro;
    private String goodPrice;

    public Good() {
    }

    public Good(String goodName, String goodIntro, String goodPrice) {
        this.goodName = goodName;
        this.goodIntro = goodIntro;
        this.goodPrice = goodPrice;
    }

    public String getGoodName() {
        return goodName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getGoodIntro() {
        return goodIntro;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setGoodIntro(String goodIntro) {
        this.goodIntro = goodIntro;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

}
