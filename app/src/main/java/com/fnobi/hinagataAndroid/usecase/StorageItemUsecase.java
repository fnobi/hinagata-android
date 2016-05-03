package com.fnobi.hinagataAndroid.usecase;

import android.content.Context;

import com.fnobi.hinagataAndroid.storage.StorageItem;
import io.realm.Realm;
import io.realm.RealmQuery;

public class StorageItemUsecase {
    private final static String DB_NAME = "hinagata-android-db";
    
    private final Context mContext;
    private final Realm mRealm;
    
    public StorageItemUsecase(Context context) {
        mContext = context;
        mRealm = Realm.getInstance(context, DB_NAME);
    }
    
    public void createItem(String title) {
        StorageItem storageItem; 
        mRealm.beginTransaction();
        {
            storageItem = mRealm.createObject(StorageItem.class);
            storageItem.setTitle(title);
        }
        mRealm.commitTransaction();
    }
    
    public StorageItem getItemById(int id) {
        RealmQuery<StorageItem> query = mRealm.where(StorageItem.class);
        query.equalTo("id", id);
        return query.findFirst();
    }
}