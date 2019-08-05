package com.fungames.galaxyshooter.ads.listener;

import android.view.View;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.constant.AdConfigConstant;
import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.MainView;
import com.google.android.gms.ads.AdRequest;
import com.ironsource.mediationsdk.IronSource;
import com.vungle.warren.AdConfig;
import com.vungle.warren.Vungle;

/**
 * Created by daipeng on 2019/7/21.
 */

public class BaseClickListener implements View.OnClickListener {

    private MainView mainView;
    private RewardedVideoAd fbRewardedVideoAd;
    private com.google.android.gms.ads.reward.RewardedVideoAd admobRewardedVideoAd;

    public BaseClickListener(MainView mainView, RewardedVideoAd fbRewardedVideoAd,
                             com.google.android.gms.ads.reward.RewardedVideoAd admobRewardedVideoAd) {
        this.mainView = mainView;
        this.fbRewardedVideoAd = fbRewardedVideoAd;
        this.admobRewardedVideoAd = admobRewardedVideoAd;
    }

    @Override
    public void onClick(View v) {
        //禁用元素操作
        v.setClickable(false);

        //移除倒计时handler
        mainView.getmCountDownHandler().removeMessages(ConstantUtil.RESURRECTION_COUNT);

        //show fb ads
        fbRewardedVideoAd.setAdListener(new FacebookRewardedVideoAdListener(fbRewardedVideoAd, mainView));
        //AdSettings.addTestDevice("2b29c369-3664-4c70-a5e9-7b1c0357cf76");
        fbRewardedVideoAd.loadAd();

        //show admob ads
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("6AD329453E75AE00087B9BA5A7B6C67A")
                .build();
        mainView.getAdmobRewardedVideoAd().setRewardedVideoAdListener(new AdmobRewardedVideoAdListener(mainView));
        mainView.getAdmobRewardedVideoAd().loadAd(AdConfigConstant.ADMOB_UNIT_ID, adRequest);

        //show iron ads
        IronSource.setRewardedVideoListener(new IronSourceRewardedVideoListener(mainView));
        IronSource.showRewardedVideo("Game_Over");

        //show vungle ads
        VungleRewardedVideoAdListener vungleRewardedVideoAdListener = new VungleRewardedVideoAdListener(mainView);
        if (Vungle.canPlayAd(AdConfigConstant.VUNGLE_PLACEMENT_ID)){
            AdConfig adConfig = new AdConfig();
            adConfig.setAutoRotate(true);
            adConfig.setMuted(true);
            Vungle.playAd(AdConfigConstant.VUNGLE_PLACEMENT_ID, adConfig, vungleRewardedVideoAdListener);
        }else{
            mainView.adsRewardedVideoClosedHandler(true);
        }

    }
}
