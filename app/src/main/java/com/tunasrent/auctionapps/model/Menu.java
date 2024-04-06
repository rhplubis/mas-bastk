package com.tunasrent.auctionapps.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String id;
    private String nama;
    private String desc;
    private String gambar;

    public Menu(String id, String nama, String desc, String gambar) {
        this.id = id;
        this.nama = nama;
        this.desc = desc;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}