package com.yoesuv.infomadiun.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.yoesuv.infomadiun.data.Constant;

/**
 *  Created by yusuf on 7/28/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(Constant.TAG_DEBUG,"MyFirebaseInstanceIDService # token : "+refreshedToken);
    }

}
