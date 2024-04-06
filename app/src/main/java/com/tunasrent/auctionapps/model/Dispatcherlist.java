package com.tunasrent.auctionapps.model;

import java.io.Serializable;

public class Dispatcherlist implements Serializable {
    String date;
    String nopol;
    String type;
//    String tahun;
    String vendor;
    String status;

    public Dispatcherlist(String date, String nopol, String type, String vendor, String status){
        this.date = date;
        this.nopol = nopol;
        this.type = type;
//        this.tahun = tahun;
        this.vendor = vendor;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
