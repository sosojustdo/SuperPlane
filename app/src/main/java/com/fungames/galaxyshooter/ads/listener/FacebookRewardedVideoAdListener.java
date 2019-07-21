package com.fungames.galaxyshooter.ads.listener;

import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.fungames.galaxyshooter.view.MainView;

/**
 * Created by daipeng on 2019/7/20.
 */

public class FacebookRewardedVideoAdListener implements RewardedVideoAdListener {

    private final String TAG = FacebookRewardedVideoAdListener.class.getSimpleName();

    private RewardedVideoAd rewardedVideoAd;
    private MainView mainView;


    public FacebookRewardedVideoAdListener(RewardedVideoAd rewardedVideoAd, MainView mainView) {
        this.rewardedVideoAd = rewardedVideoAd;
        this.mainView = mainView;
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d(TAG, "Rewarded video completed!");
    }

    @Override
    public void onError(Ad ad, AdError adError) {
        Log.e(TAG, "Rewarded video ad failed to load: " + adError.getErrorMessage());
    }

    @Override
    public void onAdLoaded(Ad ad) {
        Log.d(TAG, "Rewarded video ad is loaded and ready to be displayed!");
        rewardedVideoAd.show();
    }

    @Override
    public void onAdClicked(Ad ad) {
        Log.d(TAG, "Rewarded video ad clicked!");
    }

    @Override
    public void onLoggingImpression(Ad ad) {
        Log.d(TAG, "Rewarded video ad impression logged!");
    }

    @Override
    public void onRewardedVideoClosed() {
        Log.d(TAG, "Rewarded video ad closed!");
        mainView.getPopupWindow().dismiss();
        mainView.adsRewardedVideoClosedHandler();
    }
}
