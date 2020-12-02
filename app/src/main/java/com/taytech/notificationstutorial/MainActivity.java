package com.taytech.notificationstutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.taytech.notificationstutorial.App.CHANNEL_1_ID;
import static com.taytech.notificationstutorial.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.notificationManager = NotificationManagerCompat.from(this);

        this.editTextTitle = findViewById(R.id.edit_text_title);
        this.editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View view) {

        String title = this.editTextTitle.getText().toString().trim();
        String message = this.editTextMessage.getText().toString().trim();

        //notification on click===============================================

        Intent intentActivity = new Intent(this, MainActivity.class);

        //creating an 'appending intent' for on clicks on notification
        PendingIntent intentContent = PendingIntent.getActivity(
                this
                , 0
                , intentActivity
                , 0);

        //notification button on click==========================================

        //creating a broadcast intent
        Intent intentBroadCast = new Intent(this, NotificationReceiver.class);
        intentBroadCast.putExtra("toastMessage", message);

        PendingIntent intentAction = PendingIntent.getBroadcast(
                this
                , 0
                , intentBroadCast
                , PendingIntent.FLAG_UPDATE_CURRENT);

        //end====================================================================

        //note setPriority is only required for API < 26
        Notification notification = new NotificationCompat
                .Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_channel_1)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setOnlyAlertOnce(true) //will not disturb when notification content updates
                .setAutoCancel(true) // dismiss on click
                .setContentIntent(intentContent) //attaching the intent for on click
                .addAction(R.mipmap.ic_launcher, "Click me", intentAction) //attaching a button
                .build();

        //same id each time would delete previous notification.
        // different will create a new notification each time
        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View view) {

        String title = this.editTextTitle.getText().toString().trim();
        String message = this.editTextMessage.getText().toString().trim();

        //note setPriority is only required for API < 26
        Notification notification = new NotificationCompat
                .Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_channel_2)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        //same id each time would delete previous notification.
        // different will create a new notification each time
        notificationManager.notify(2, notification);
    }
}