package com.taytech.notificationstutorial;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel_1";
    public static final String CHANNEL_2_ID = "channel_2";

    //this onCreate will be called before onCreate of any activity
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {

        //if our android version is greater or equal to OREO
        //notification channels are only used on API >= 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //1st Channel
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);

            channel1.setDescription("This is a channel 1");

            //2nd Channel
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW);

            channel2.setDescription("This is a channel 2");

            //Create the channels
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            //always check objects created through getSystemService
            if (notificationManager != null) {

                notificationManager.createNotificationChannel(channel1);
                notificationManager.createNotificationChannel(channel2);
            }
        }
    }
}
