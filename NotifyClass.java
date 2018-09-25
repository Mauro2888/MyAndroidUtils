package com.example.maurocaredda.test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;


public class Notify extends ContextWrapper {

    private NotificationManager mNotificationManager;

    public Notify(Context base) {
        super(base);
        createNotification();
    }

    public void createNotification(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            NotificationChannel channel = new NotificationChannel(
                    StaticValues.ID_CHANNEL,
                    StaticValues.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(channel);
        }
    }

    private NotificationManager getManager(){
        if (mNotificationManager == null){
            mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), StaticValues.ID_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }
}
