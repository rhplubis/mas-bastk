package com.tunasrent.auctionapps.DB;

import io.realm.RealmObject;

public class InputData extends RealmObject {
    String vendorid;
    String vendor;
    String tanggal;
    String pukul;
    String lokasi_penarikan;
    String merk;
    int type;

    int statusKunci;
    int kondisiKunci;
    String vhcTypeid;
    String vhcTypename;
    String vhcItemid;
    String tahun;

    String TotalKunci;
    String stnk;
    String nopol1;
    String nopol2;
    String nopol3;
    String kmditarik;
    String meterbensin;
    String cabang;
    String nomesin;
    String norangka;
    String warna;
    String kmditerima;
//    String cficabang;
    String lokasi_pool;
    String lokasi_poolid;


    public String getVendorid() {
        return vendorid;
    }

    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPukul() {
        return pukul;
    }

    public void setPukul(String pukul) {
        this.pukul = pukul;
    }

    public String getLokasi_penarikan() {
        return lokasi_penarikan;
    }

    public void setLokasi_penarikan(String lokasi_penarikan) {
        this.lokasi_penarikan = lokasi_penarikan;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatusKunci() {
        return statusKunci;
    }

    public void setStatusKunci(int statusKunci) {
        this.statusKunci = statusKunci;
    }

    public int getKondisiKunci() {
        return kondisiKunci;
    }

    public void setKondisiKunci(int kondisiKunci) {
        this.kondisiKunci = kondisiKunci;
    }

    public String getVhcTypeid() {
        return vhcTypeid;
    }

    public void setVhcTypeid(String vhcTypeid) {
        this.vhcTypeid = vhcTypeid;
    }

    public String getVhcTypename() {
        return vhcTypename;
    }

    public void setVhcTypename(String vhcTypename) {
        this.vhcTypename = vhcTypename;
    }

    public String getVhcItemid() {
        return vhcItemid;
    }

    public void setVhcItemid(String vhcItemid) {
        this.vhcItemid = vhcItemid;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getTotalKunci() {
        return TotalKunci;
    }

    public void setTotalKunci(String TotalKunci) {
        this.TotalKunci = TotalKunci;
    }

    public String getStnk() {
        return stnk;
    }

    public void setStnk(String stnk) {
        this.stnk = stnk;
    }

    public String getNopol1() {
        return nopol1;
    }

    public void setNopol1(String nopol1) {
        this.nopol1 = nopol1;
    }

    public String getNopol2() {
        return nopol2;
    }

    public void setNopol2(String nopol2) {
        this.nopol2 = nopol2;
    }

    public String getNopol3() {
        return nopol3;
    }

    public void setNopol3(String nopol3) {
        this.nopol3 = nopol3;
    }

    public String getKmditarik() {
        return kmditarik;
    }

    public void setKmditarik(String kmditarik) {
        this.kmditarik = kmditarik;
    }

    public String getMeterbensin() {
        return meterbensin;
    }

    public void setMeterbensin(String meterbensin) {
        this.meterbensin = meterbensin;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getNomesin() {
        return nomesin;
    }

    public void setNomesin(String nomesin) {
        this.nomesin = nomesin;
    }

    public String getNorangka() {
        return norangka;
    }

    public void setNorangka(String norangka) {
        this.norangka = norangka;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKmditerima() {
        return kmditerima;
    }

    public void setKmditerima(String kmditerima) {
        this.kmditerima = kmditerima;
    }

//    public String getCficabang() {
//        return cficabang;
//    }
//
//    public void setCficabang(String cficabang) {
//        this.cficabang = cficabang;
//    }

    public String getLokasi_pool() {
        return lokasi_pool;
    }

    public void setLokasi_pool(String lokasi_pool) {
        this.lokasi_pool = lokasi_pool;
    }

    public String getLokasi_poolid() {
        return lokasi_poolid;
    }

    public void setLokasi_poolid(String lokasi_poolid) {
        this.lokasi_poolid = lokasi_poolid;
    }
}
