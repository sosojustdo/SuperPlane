package com.fungames.galaxyshooter.handler;

import android.widget.PopupWindow;

/**
 * Created by daipeng on 2019/7/11.
 */

public class CountDownHandlerObject {

    private int initTimer;//复活倒计时，单位：秒

    private PopupWindow popupWindow;//复活弹框

    public CountDownHandlerObject(int initTimer, PopupWindow popupWindow) {
        this.initTimer = initTimer;
        this.popupWindow = popupWindow;
    }

    public CountDownHandlerObject(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }


    public int getInitTimer() {
        return initTimer;
    }

    public PopupWindow getPopupWindow() {
        return popupWindow;
    }

    public void setInitTimer(int initTimer) {
        this.initTimer = initTimer;
    }

    public void setPopupWindow(PopupWindow popupWindow) {
        this.popupWindow = popupWindow;
    }
}
