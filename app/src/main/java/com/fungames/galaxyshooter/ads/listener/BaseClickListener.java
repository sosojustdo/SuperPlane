package com.fungames.galaxyshooter.ads.listener;

import android.view.View;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.R;
import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.MainView;
import com.google.android.gms.ads.AdRequest;

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
        //fbRewardedVideoAd.setAdListener(new FacebookRewardedVideoAdListener(fbRewardedVideoAd, mainView));
        //fbRewardedVideoAd.loadAd();

        //show admob ads
        mainView.getAdmobRewardedVideoAd().setRewardedVideoAdListener(new AdmobRewardedVideoAdListener(admobRewardedVideoAd, mainView));
        mainView.getAdmobRewardedVideoAd().loadAd(mainView.getMainActivity().getBaseContext().getString(R.string.admob_unit_id), new AdRequest.Builder().build());
        if(mainView.getAdmobRewardedVideoAd().isLoaded()){
            mainView.getAdmobRewardedVideoAd().show();
        }


        //show iron ads
        //IronSource.setRewardedVideoListener(new IronSourceRewardedVideoListener(mainView));
        //IronSource.showRewardedVideo();

    }
}
