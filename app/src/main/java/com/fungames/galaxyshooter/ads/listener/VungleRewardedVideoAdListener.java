package com.fungames.galaxyshooter.ads.listener;

import android.util.Log;

import com.fungames.galaxyshooter.constant.AdConfigConstant;
import com.fungames.galaxyshooter.view.MainView;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;

/**
 * @desc:
 * @author: daipeng1
 * @date: 2019-08-02 22:51
 */
public class VungleRewardedVideoAdListener implements PlayAdCallback {

    private final String TAG = VungleRewardedVideoAdListener.class.getSimpleName();

    private MainView mainView;

    public VungleRewardedVideoAdListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onAdStart(String id) {
        Log.d(TAG, "Vungle Rewarded video start play!");
    }

    @Override
    public void onAdEnd(String id, boolean completed, boolean isCTAClicked) {
        Log.d(TAG, String.format("Vungle Rewarded video play end or close!, placementid:%s, completed:%s, isCTAClicked:%s, canPlay:%s",
                id, completed, isCTAClicked, Vungle.canPlayAd(AdConfigConstant.VUNGLE_PLACEMENT_ID)));
        mainView.adsRewardedVideoClosedHandler(true);
    }

    @Override
    public void onError(String id, Throwable cause) {
        Log.d(TAG, "Vungle Rewarded video load or play error, placementId:" + id + "error info:" + cause);
    }
}
