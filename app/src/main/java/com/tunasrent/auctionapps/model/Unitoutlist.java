package com.tunasrent.auctionapps.model;

import java.io.Serializable;

public class Unitoutlist implements Serializable {

    String vhcid, vhctype, vhccolor, vhcyear, vendor, status,procby,type_aset, jenisunit;

//    String vhcid,
//    String date;
//    String nopol;
//    String type;
//    String vendor;
//    String status;
//    String by;
//    String bar;

    public Unitoutlist( String vhcid, String vhctype, String vhccolor, String vhcyear, String vendor, String status , String procby , String type_aset, String jenisunit){
        this.vhcid = vhcid ;
        this.vhctype = vhctype;
        this.vhccolor = vhccolor;
        this.vendor = vendor;
        this.status = status;
        this.vhcyear = vhcyear;
        this.procby = procby;
        this.type_aset = type_aset;
        this.jenisunit = jenisunit;
    }

    //public String getDate() {
      //  return date;
    //}

//    public void setDate(String date) {
//        this.date = date;
//    }
    public String getVhccolor() {
        return vhccolor;
    }

    public void setVhccolor(String vhccolor) {
        this.vhccolor = vhccolor;
    }

    public String getVhcyear() {
        return vhcyear;
    }

    public void setVhcyear(String vhcyear) {
        this.vhcyear = vhcyear;
    }

    public String getNopol() {
        return vhcid;
    }

    public void setNopol(String vhcid) {
        this.vhcid = vhcid;
    }

    public String getType() {
        return vhctype;
    }

    public void setType(String vhctype) {
        this.vhctype = vhctype;
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

    public String getBy() {
        return procby;
    }

    public void setBy(String procby) {
        this.procby = procby;
    }

    public String getBar() {
        return type_aset;
    }

    public void setBar(String type_aset) {
        this.type_aset = type_aset;
    }

    public String getJenisunit(){ return jenisunit;}

    public void setJenisunit(String jenisunit){ this.jenisunit = jenisunit;}
}
