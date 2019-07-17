package com.fungames.galaxyshooter.handler;

import razerdp.widget.QuickPopup;

/**
 * Created by daipeng on 2019/7/11.
 */

public class CountDownHandlerObject {

    private int initTimer;//复活倒计时，单位：秒

    private QuickPopup quickPopup;//复活弹框

    public CountDownHandlerObject(int initTimer, QuickPopup quickPopup) {
        this.initTimer = initTimer;
        this.quickPopup = quickPopup;
    }

    public int getInitTimer() {
        return initTimer;
    }

    public QuickPopup getQuickPopup() {
        return quickPopup;
    }

    public void setInitTimer(int initTimer) {
        this.initTimer = initTimer;
    }

    public void setQuickPopup(QuickPopup quickPopup) {
        this.quickPopup = quickPopup;
    }

}
