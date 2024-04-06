package com.tunasrent.auctionapps;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by yuli on 13-Mar-20.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
//    @Override
//    public void onTokenRefresh() {
////        String token = FirebaseInstanceId.getInstance().getToken();
////        registerToken(token);
////        Log.d("lihattoken","hasil" + token);
//    }
//    private void registerToken(String token){
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("device_id",token)
//                .build();
//        Request request = new Request.Builder()
//                .url(konfigurasi.URL_REQDEVICE)
//                .post(body)
//                .build();
//        try {
//            client.newCall(request).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
}
