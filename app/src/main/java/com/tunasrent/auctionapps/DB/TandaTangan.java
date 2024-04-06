package com.tunasrent.auctionapps.DB;

import io.realm.RealmObject;

public class TandaTangan extends RealmObject {
    String startDate;
    String endDate;
    String noPembiayaan;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNoPembiayaan() {
        return noPembiayaan;
    }

    public void setNoPembiayaan(String noPembiayaan) {
        this.noPembiayaan = noPembiayaan;
    }
}
