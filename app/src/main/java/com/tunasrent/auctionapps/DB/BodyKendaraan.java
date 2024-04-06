package com.tunasrent.auctionapps.DB;

import io.realm.RealmObject;

public class BodyKendaraan extends RealmObject {
    String picPenarikan;
    String picPenyimpanan;
    String picPenjualan;

    public String getPicPenarikan() {
        return picPenarikan;
    }

    public void setPicPenarikan(String picPenarikan) {
        this.picPenarikan = picPenarikan;
    }

    public String getPicPenyimpanan() {
        return picPenyimpanan;
    }

    public void setPicPenyimpanan(String picPenyimpanan) {
        this.picPenyimpanan = picPenyimpanan;
    }

    public String getPicPenjualan() {
        return picPenjualan;
    }

    public void setPicPenjualan(String picPenjualan) {
        this.picPenjualan = picPenjualan;
    }
}
