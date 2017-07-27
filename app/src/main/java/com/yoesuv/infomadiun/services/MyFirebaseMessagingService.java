package com.yoesuv.infomadiun.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yoesuv.infomadiun.data.Constant;

/**
 *  Created by yusuf on 7/28/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(Constant.TAG_DEBUG,"MyFirebaseMessagingService # onMessageReceived : "+remoteMessage.getFrom());
        Log.d(Constant.TAG_DEBUG,"MyFirebaseMessagingService # onMessageReceived : "+remoteMessage.getNotification().getBody());
    }
}
