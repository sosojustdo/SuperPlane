package com.fungames.galaxyshooter.ads.listener;

import android.util.Log;

import com.fungames.galaxyshooter.view.MainView;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by daipeng on 2019/7/21.
 */

public class AdmobRewardedVideoAdListener implements RewardedVideoAdListener {

    private final String TAG = AdmobRewardedVideoAdListener.class.getSimpleName();

    private RewardedVideoAd rewardedVideoAd;
    private MainView mainView;

    public AdmobRewardedVideoAdListener(RewardedVideoAd rewardedVideoAd, MainView mainView) {
        this.rewardedVideoAd = rewardedVideoAd;
        this.mainView = mainView;
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d(TAG, "Rewarded video ad loaded!");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d(TAG, "Rewarded video ad opened!");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d(TAG, "Rewarded video ad started!");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d(TAG, "Rewarded video ad closed!");
        mainView.adsRewardedVideoClosedHandler();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.d(TAG, "Rewarded video ad onRewarded!");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.d(TAG, "Rewarded video ad onRewardedVideoAdLeftApplication!");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.d(TAG, "Rewarded video ad failed load!");
        mainView.adsRewardedVideoClosedHandler();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d(TAG, "Rewarded video ad completed!");
    }
}
