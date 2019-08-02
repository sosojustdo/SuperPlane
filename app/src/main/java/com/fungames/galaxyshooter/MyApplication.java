package com.fungames.galaxyshooter;

import android.app.Application;
import android.util.Log;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.fungames.galaxyshooter.constant.AdConfigConstant;
import com.google.android.gms.ads.MobileAds;
import com.vungle.warren.InitCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleSettings;

/**
 * Created by daipeng on 2019/7/18.
 */

public class MyApplication extends Application {

    private final String TAG = MyApplication.class.getSimpleName();

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
        VungleSettings vungleSettings = new VungleSettings.Builder()
                .setMinimumSpaceForInit(51L * 1024L * 1024L)  // 51 MB
                .setMinimumSpaceForAd(50L * 1024L * 1024L)    // 50 MB
                .build();
        Vungle.init(AdConfigConstant.VUNGLE_APP_ID, getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                // Initialization has succeeded and SDK is ready to load an ad or play one if there
                // is one pre-cached already
                Log.d(TAG, "Vungle rewarded video ad init success...");
            }

            @Override
            public void onError(Throwable throwable) {
                // Initialization error occurred - throwable.getLocalizedMessage() contains error message
                Log.d(TAG, "Vungle rewarded video ad init error:" + throwable.getMessage());
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {
                // Callback to notify when an ad becomes available for the cache optimized placement
                // NOTE: This callback works only for the cache optimized placement. Otherwise, please use
                // LoadAdCallback with loadAd API for loading placements.
                Log.d(TAG, "Vungle rewarded video ad init auto cache available, placementId:" + placementId);
            }
        }, vungleSettings);

    }

}
