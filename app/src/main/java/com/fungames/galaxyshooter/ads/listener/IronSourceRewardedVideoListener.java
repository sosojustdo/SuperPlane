package com.fungames.galaxyshooter.ads.listener;

import android.os.Message;
import android.util.Log;

import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.handler.CountDownHandlerObject;
import com.fungames.galaxyshooter.view.MainView;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

/**
 * Created by daipeng on 2019/7/21.
 */

public class IronSourceRewardedVideoListener implements RewardedVideoListener {

    private final String TAG = IronSourceRewardedVideoListener.class.getSimpleName();

    private MainView mainView;

    public IronSourceRewardedVideoListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d(TAG, "Rewarded video ad opened!");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d(TAG, "Rewarded video ad closed!");
        Message message = new Message();
        message.arg1 = 4;
        message.arg2 = 5;
        message.what = ConstantUtil.POPUP_DISMISS;
        CountDownHandlerObject object = new CountDownHandlerObject(mainView.getPopupWindow());
        message.obj = object;
        mainView.getmCountDownHandler().sendMessage(message);
    }

    @Override
    public void onRewardedVideoAvailabilityChanged(boolean b) {
        Log.d(TAG, "Rewarded video availability:" + b);
    }

    @Override
    public void onRewardedVideoAdStarted() {
        Log.d(TAG, "Rewarded video ad started!");
    }

    @Override
    public void onRewardedVideoAdEnded() {
        Log.d(TAG, "Rewarded video ad ended!");
    }

    @Override
    public void onRewardedVideoAdRewarded(Placement placement) {
        Log.d(TAG, "Rewarded video ad rewarded!");
    }

    @Override
    public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
        Log.d(TAG, "Rewarded video ad error:" + ironSourceError.getErrorMessage());
        Message message = new Message();
        message.arg1 = 4;
        message.arg2 = 5;
        message.what = ConstantUtil.POPUP_DISMISS;
        CountDownHandlerObject object = new CountDownHandlerObject(mainView.getPopupWindow());
        message.obj = object;
        mainView.getmCountDownHandler().sendMessage(message);
    }

    @Override
    public void onRewardedVideoAdClicked(Placement placement) {
        Log.d(TAG, "Rewarded video ad clicked!");
    }
}
