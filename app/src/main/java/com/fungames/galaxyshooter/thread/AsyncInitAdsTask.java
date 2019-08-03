package com.fungames.galaxyshooter.thread;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.R;
import com.fungames.galaxyshooter.ads.listener.IronSourceRewardedVideoListener;
import com.fungames.galaxyshooter.constant.AdConfigConstant;
import com.fungames.galaxyshooter.view.MainView;
import com.google.android.gms.ads.MobileAds;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.vungle.warren.AdConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleSettings;

import java.util.Arrays;
import java.util.List;

import static com.vungle.warren.Vungle.getValidPlacements;

/**
 * @desc:
 * @author: daipeng1
 * @date: 2019-07-27 18:57
 */
public class AsyncInitAdsTask extends AsyncTask<MainView, Integer, Void> {

    private final String TAG = AsyncInitAdsTask.class.getSimpleName();

    private RewardedVideoAd fbRewardedVideoAd;//facebook激励视频
    private com.google.android.gms.ads.reward.RewardedVideoAd admobRewardedVideoAd;//admob激励视频

    public AsyncInitAdsTask(RewardedVideoAd fbRewardedVideoAd, com.google.android.gms.ads.reward.RewardedVideoAd admobRewardedVideoAd) {
        this.fbRewardedVideoAd = fbRewardedVideoAd;
        this.admobRewardedVideoAd = admobRewardedVideoAd;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(MainView... mainViews) {
        List<MainView> mainViewList = Arrays.asList(mainViews);
        MainView mainView = mainViewList.get(0);

        fbRewardedVideoAd = new RewardedVideoAd(mainView.getContext(), AdConfigConstant.FACEBOOK_PLACEMENT_ID);

        admobRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(mainView.getContext());
        //admobRewardedVideoAd.loadAd(mainView.getContext().getString(R.string.admob_unit_id), new AdRequest.Builder().build());

        //init iron source sdk
        IronSource.shouldTrackNetworkState(mainView.getContext(), true);
        IntegrationHelper.validateIntegration(mainView.getMainActivity());
        IronSource.init(mainView.getMainActivity(), AdConfigConstant.IRON_SOURCE_APPKEY, IronSource.AD_UNIT.REWARDED_VIDEO);
        IronSource.setRewardedVideoListener(new IronSourceRewardedVideoListener(mainView));

        //init vungle sdk
        VungleSettings vungleSettings = new VungleSettings.Builder()
                .setMinimumSpaceForInit(51L * 1024L * 1024L)  // 51 MB
                .setMinimumSpaceForAd(50L * 1024L * 1024L)    // 50 MB
                .build();
        Vungle.init(AdConfigConstant.VUNGLE_APP_ID, mainView.getMainActivity().getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                // Initialization has succeeded and SDK is ready to load an ad or play one if there
                // is one pre-cached already
                Log.d(TAG, "Vungle rewarded video ad init success...");
                for (String validPlacementReferenceIdId : getValidPlacements()) {
                    Log.d(TAG, "PlacementReferenceIdId: " + validPlacementReferenceIdId + "init success!");
                }

                //init success again load ads
                if (Vungle.isInitialized()){
                    Vungle.loadAd(AdConfigConstant.VUNGLE_PLACEMENT_ID, new LoadAdCallback() {
                        @Override
                        public void onAdLoad(String id) {
                            Log.d(TAG, "Vungle Rewarded video load, init status:" + Vungle.isInitialized());
                        }

                        @Override
                        public void onError(String id, Throwable cause) {
                            Log.d(TAG, "Vungle Rewarded video load error, placementId:" + id + "error info:" + cause);
                        }
                    });
                }
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

        mainView.setAdmobRewardedVideoAd(admobRewardedVideoAd);
        mainView.setFbRewardedVideoAd(fbRewardedVideoAd);
        return null;
    }
}
