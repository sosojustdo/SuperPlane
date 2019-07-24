package com.fungames.galaxyshooter;

import android.app.Application;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.vungle.warren.InitCallback;
import com.vungle.warren.Vungle;

/**
 * Created by daipeng on 2019/7/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //init facebook sdk
        AudienceNetworkAds.initialize(this);
        //设置错误处理触发onError
        AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE);

        //init admob sdk
        MobileAds.initialize(this, this.getString(R.string.admob_id));

        //init vungle sdk
        Vungle.init(this.getString(R.string.vungle_app_id), getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                // Initialization has succeeded and SDK is ready to load an ad or play one if there
                // is one pre-cached already
            }

            @Override
            public void onError(Throwable throwable) {
                // Initialization error occurred - throwable.getLocalizedMessage() contains error message
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {
                // Callback to notify when an ad becomes available for the cache optimized placement
                // NOTE: This callback works only for the cache optimized placement. Otherwise, please use
                // LoadAdCallback with loadAd API for loading placements.
            }
        });

    }

}
