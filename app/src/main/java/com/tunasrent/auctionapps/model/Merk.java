package com.tunasrent.auctionapps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Merk {
    String item_id;
    String item_name;
    String item_type_id;
    String item_type_name;

    public Merk(String item_id, String item_name,String item_type_id,String item_type_name){
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type_id = item_type_id;
        this.item_type_name = item_type_name;

    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_type_id(String item_type_id) {
        this.item_type_id = item_type_id;
    }

    public void setItem_type_name(String item_type_name) {
        this.item_type_name = item_type_name;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_type_id() {
        return item_type_id;
    }

    public String getItem_type_name() {
        return item_type_name;
    }
}
