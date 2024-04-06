package com.tunasrent.auctionapps.model;

import java.io.Serializable;

public class DispatcherlistBastk implements Serializable {
    String date;
    String nopol;
    String type;
    String vendor;
    String status;
    String flag;

    public DispatcherlistBastk(String date, String nopol, String type, String vendor, String status, String flag){
        this.date = date;
        this.nopol = nopol;
        this.type = type;
        this.vendor = vendor;
        this.status = status;
        this.flag = flag;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
