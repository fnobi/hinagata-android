package com.fnobi.hinagataAndroid.storage;

import io.realm.RealmObject;

public class StorageItem extends RealmObject {
    
    private int id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
