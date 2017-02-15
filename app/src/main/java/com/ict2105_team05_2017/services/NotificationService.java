package com.ict2105_team05_2017.services;


import android.app.Notification;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ict2105_team05_2017.R;
import com.ict2105_team05_2017.activities.MainActivity;

import java.util.Map;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = NotificationService.class.getName();

    /*
    * onMessageReceived: fro handling messages once they are received by the device*/
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        /*
        * If the application is in the foreground the data is handled from here
        * */
        Log.e(TAG, " This is the message: " + remoteMessage.getData());
        String msg = remoteMessage.getNotification().getBody();
        Log.e(TAG, " This is the Notification: " + msg);
        Log.e(TAG, " This is the Data: " + remoteMessage.getData());
        Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        Log.e(TAG," This is the title "+title);
        String message= data.get("message");
        Log.e(TAG, "This is message "+message );



        fireNotification(msg, title);

    }

    private void fireNotification(String msg, String title) {

        PugNotification.with(this)
                .load()
                .title(title)
                .message(msg)

                .bigTextStyle("You have a new Message")
                .smallIcon(R.drawable.ic_drop)
                .largeIcon(R.drawable.ic_drop)
                .sound(Uri.EMPTY)
                .flags(Notification.DEFAULT_ALL)
                .autoCancel(true)
                .click(MainActivity.class)
                .simple()
                .build();
    }
}
