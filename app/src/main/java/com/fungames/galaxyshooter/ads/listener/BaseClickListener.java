package com.fungames.galaxyshooter.ads.listener;

import android.view.View;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.MainView;

/**
 * Created by daipeng on 2019/7/21.
 */

public class BaseClickListener implements View.OnClickListener {


    private final String TAG = BaseClickListener.class.getSimpleName();

    private MainView mainView;
    private RewardedVideoAd fbRewardedVideoAd;

    public BaseClickListener(MainView mainView, RewardedVideoAd fbRewardedVideoAd) {
        this.mainView = mainView;
        this.fbRewardedVideoAd = fbRewardedVideoAd;
    }

    @Override
    public void onClick(View v) {
        //禁用元素操作
        v.setClickable(false);

        //移除倒计时handler
        mainView.getmCountDownHandler().removeMessages(ConstantUtil.RESURRECTION_COUNT);

        //show fb ads
        fbRewardedVideoAd.setAdListener(new FacebookRewardedVideoAdListener(fbRewardedVideoAd, mainView));
        fbRewardedVideoAd.loadAd();

        //show admob ads
    }
}
