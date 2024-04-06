package com.tunasrent.auctionapps;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.tunasrent.auctionapps.mobilisasi.MobListActivity;

/**
 * Created by yuli on 13-Mar-20.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private int count = 0;
    private NotificationChannel mChannel;
    private NotificationManager notifManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("msg", "onMessageReceived: " + remoteMessage.getData());
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("type",remoteMessage.getData().get("type"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_icon_new)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());



        //Show Count Badge
//        try {
//            Badges.setBadge(this,++count);
//        }catch (BadgesNotSupportedException error){
//            Toast.makeText(this,"Thats error..!!",Toast.LENGTH_SHORT).show();
//        }

//        Log.d("cek_remotemsg", "msg: " + remoteMessage.getData());
//
//        String type = remoteMessage.getData().get("type");
//        //String message = remoteMessage.getData().get("content");
//
//        String message = remoteMessage.getNotification().getBody();
//        String title = remoteMessage.getNotification().getTitle();
//        //String click_action = remoteMessage.getNotification().getClickAction();
//        Intent intent = new Intent(this,LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//        notificationBuilder.setContentTitle(title);
//        notificationBuilder.setContentText(message);
//        notificationBuilder.setSmallIcon(R.mipmap.ic_car);
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
////            notificationManager.createNotificationChannel(channel);
////        }
//        notificationManager.notify(0,notificationBuilder.build());

        //showNotification(type,message);
//        showNotification(remoteMessage.getData().get("message"));
//        showNotification(remoteMessage.getNotification().getBody());
//        if (remoteMessage.getData().get("type").equals(1)){
//
//        }else if (remoteMessage.getData().get("type").equals(2)){
//
//        }else if (remoteMessage.getData().get("type").equals(3)) {
//
//        }
//        if (remoteMessage.getData().get("type").equals("1")) {
//            Log.d("status","masuk intent");
//            Intent i = new Intent(this,PopupActivity.class);
//            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//        }


//        Log.d("cek_data", "type: " + remoteMessage.getData().get("username"));
//        Log.d("cek_datas1", "type: " + remoteMessage.getData().get("img"));
//        Log.d("cek_datas2", "type: " + remoteMessage.getData().get("type"));//type
//        Log.d("cek_datas3", "Message data message: " + remoteMessage.getData().get("content"));
//        Log.d("cek_datas4", "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        Log.d("cek_datas5", "Message Notification Title: " + remoteMessage.getNotification().getTitle());

//        Intent intent = new Intent(this,InboxActivity.class);
//        Bundle b = new Bundle();
//        b.putString("parse_username",remoteMessage.getData().get("username"));
//        intent.putExtras(b);
//        startActivity(intent);
    }
    private void showNotification(String type, String message){
            Log.d("cek_msg", "Message Notification: " + type + " " + message);
            Intent i;
            if (type.equals("5")){
                i = new Intent(getApplicationContext(), MobListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }else {
                i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.
                    FLAG_ONE_SHOT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle("Tunas Auction")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .setContentText(message)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    //.setSound(sound)
                    .setSmallIcon(R.mipmap.ic_car)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(0,builder.build());
        }
    //}
//    private int getNotificationIcon() {
//        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
//        return useWhiteIcon ? R.mipmap.absen_icon : R.mipmap.absen_icon;
//    }
}
