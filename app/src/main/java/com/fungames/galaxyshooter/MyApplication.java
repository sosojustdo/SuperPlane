package com.fungames.galaxyshooter;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;

/**
 * Created by daipeng on 2019/7/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init facebook sdk
        AudienceNetworkAds.initialize(this);
    }
}
