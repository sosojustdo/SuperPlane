package com.fungames.galaxyshooter.thread;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.R;
import com.fungames.galaxyshooter.view.MainView;
import com.google.android.gms.ads.MobileAds;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;

import java.util.Arrays;
import java.util.List;

/**
 * @desc:
 * @author: daipeng1
 * @date: 2019-07-27 18:57
 */
public class AsyncInitAdsTask extends AsyncTask<MainView, Integer, Void> {

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

        fbRewardedVideoAd = new RewardedVideoAd(mainView.getContext(), mainView.getContext().getString(R.string.placement_id));

        admobRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(mainView.getMainActivity().getBaseContext());
        //admobRewardedVideoAd.loadAd(mainView.getContext().getString(R.string.admob_unit_id), new AdRequest.Builder().build());

        IntegrationHelper.validateIntegration(mainView.getMainActivity());
        IronSource.shouldTrackNetworkState(mainView.getContext(), true);
        IronSource.init(mainView.getMainActivity(), mainView.getContext().getString(R.string.ironSource_appkey), IronSource.AD_UNIT.REWARDED_VIDEO);

        mainView.setAdmobRewardedVideoAd(admobRewardedVideoAd);
        mainView.setFbRewardedVideoAd(fbRewardedVideoAd);
        return null;
    }
}
