package com.tunasrent.auctionapps.model;

public class SetPool {

        String pool_id;
        String pool_address;

        public SetPool(String pool_id, String pool_address){
            this.pool_id = pool_id;
            this.pool_address = pool_address;
        }

        public String getPool_id() {
            return pool_id;
        }

        public String getPool_address() {
            return pool_address;
        }

        public void setPool_id(String pool_id) {
            this.pool_id = pool_id;
        }

        public void setPool_address(String pool_address) {
            this.pool_address = pool_address;
        }
}
