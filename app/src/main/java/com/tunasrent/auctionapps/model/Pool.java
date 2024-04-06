package com.tunasrent.auctionapps.model;

public class Pool {
    String pool_id;
//    String pool_name;
    String pool_address;

    public Pool(String pool_id, String pool_address){
        this.pool_id = pool_id;
//        this.pool_name = pool_name;
        this.pool_address = pool_address;
    }

    public String getPool_id() {
        return pool_id;
    }

//    public String getPool_name() {
//        return pool_name;
//    }

    public String getPool_address() {
        return pool_address;
    }
}
