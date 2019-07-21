package com.fungames.galaxyshooter;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by daipeng on 2019/7/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init facebook sdk
        AudienceNetworkAds.initialize(this);

        //init admob sdk
        MobileAds.initialize(this, this.getString(R.string.admob_id));
    }
}
