package com.fungames.galaxyshooter.ads.listener;

import android.os.Message;
import android.view.View;

import com.facebook.ads.RewardedVideoAd;
import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.MainView;

/**
 * Created by daipeng on 2019/7/20.
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
        //设置quickPopup handler事件
        Message message = mainView.getmCountDownHandler().obtainMessage();
        message.arg1 = 0;
        message.arg2 = 1;
        message.what = ConstantUtil.POPUP_DISMISS;
        message.obj = mainView.getQuickPopup();
        mainView.getmCountDownHandler().sendMessage(message);

        //移除倒计时handler
        mainView.getmCountDownHandler().removeMessages(ConstantUtil.RESURRECTION_COUNT);

        //show fb ads
        fbRewardedVideoAd.setAdListener(new FacebookRewardedVideoAdListener(fbRewardedVideoAd, mainView));
        fbRewardedVideoAd.loadAd();

        //show admob ads
    }
}
