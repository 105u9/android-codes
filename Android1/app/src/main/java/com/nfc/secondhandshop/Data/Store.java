package com.nfc.secondhandshop.Data;

public class Store {
    private String storeName;
    private String mainBusiness;
    private String storeIntro;

    public Store() {
    }

    public Store(String storeName, String mainBusiness, String storeIntro) {
        this.storeName = storeName;
        this.mainBusiness = mainBusiness;
        this.storeIntro = storeIntro;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public String getStoreIntro() {
        return storeIntro;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public void setStoreIntro(String storeIntro) {
        this.storeIntro = storeIntro;
    }
}
