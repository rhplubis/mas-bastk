package com.tunasrent.auctionapps.model;

public class Vendor {
    String vendor_id;
    String vendor;

    public Vendor(String vendor_id, String vendor){
        this.vendor_id = vendor_id;
        this.vendor = vendor;

    }

    public String getVendor_id() {
        return vendor_id;
    }

    public String getVendor() {
        return vendor;
    }
}
