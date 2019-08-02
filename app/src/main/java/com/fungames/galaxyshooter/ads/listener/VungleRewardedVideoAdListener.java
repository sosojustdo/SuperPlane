package com.fungames.galaxyshooter.ads.listener;

import android.util.Log;

import com.fungames.galaxyshooter.MyApplication;
import com.fungames.galaxyshooter.constant.AdConfigConstant;
import com.fungames.galaxyshooter.view.MainView;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

/**
 * @desc:
 * @author: daipeng1
 * @date: 2019-08-02 22:51
 */
public class VungleRewardedVideoAdListener implements LoadAdCallback, PlayAdCallback {

    private final String TAG = VungleRewardedVideoAdListener.class.getSimpleName();

    private MainView mainView;

    public VungleRewardedVideoAdListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onAdLoad(String id) {
        Log.d(TAG, "Vungle Rewarded video load, init status:" + Vungle.isInitialized());
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
        try {
            VungleException ex = (VungleException) cause;

            if (ex.getExceptionCode() == VungleException.VUNGLE_NOT_INTIALIZED) {
                Vungle.init(AdConfigConstant.VUNGLE_APP_ID, new MyApplication().getApplicationContext(), new InitCallback() {
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
                });
            }
        } catch (ClassCastException cex) {
            Log.d(TAG, cex.getMessage());
        }
    }
}
