package com.tunasrent.auctionapps.util;

/**
 * Created by Parsania Hardik on 19/01/2016.
 */
public interface AsyncTaskCompleteListener {
    void onTaskCompleted(String response, int serviceCode);

    void onTaskCompleted1(String response, int serviceCode);
}

