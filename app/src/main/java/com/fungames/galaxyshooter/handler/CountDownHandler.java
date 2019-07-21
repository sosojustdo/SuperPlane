package com.fungames.galaxyshooter.handler;

import android.os.Handler;
import android.os.Message;

import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.MainView;

import java.lang.ref.WeakReference;

import razerdp.widget.QuickPopup;

/**
 * Created by daipeng on 2019/7/11.
 */

public class CountDownHandler extends Handler {

    public final WeakReference<MainView> weakReference;

    public CountDownHandler(MainView mainView) {
        weakReference = new WeakReference<MainView>(mainView);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        MainView mainView = weakReference.get();
        switch (msg.what) {
            case ConstantUtil.RESURRECTION_COUNT:
                CountDownHandlerObject object = (CountDownHandlerObject) msg.obj;
                int timer = object.getInitTimer();
                mainView.getCountView().setText(String.valueOf(timer / 1000));
                msg = Message.obtain();//重新获取消息
                msg.arg1 = 0;
                msg.arg2 = 1;
                msg.what = ConstantUtil.RESURRECTION_COUNT;
                object.setInitTimer(timer -= 1000);
                msg.obj = object;
                QuickPopup quickPopup = object.getQuickPopup();
                if (timer > 0) {
                    sendMessageDelayed(msg, ConstantUtil.DELAY_SEND_DURATION);
                }else{
                    quickPopup.dismiss();

                    //返回游戏重新开始
                    Message message = new Message();
                    message.what = ConstantUtil.TO_END_VIEW;
                    message.arg1 = Integer.valueOf(mainView.getSumScore());
                    mainView.getMainActivity().getHandler().sendMessage(message);
                }
                break;
            case ConstantUtil.RUNNING_GAME:
                msg = Message.obtain();
                msg.arg1 = 2;
                msg.arg2 = 3;
                msg.what = ConstantUtil.RUNNING_GAME;
                mainView.initObject();
                mainView.drawSelf();
                mainView.viewLogic();
                break;
            case ConstantUtil.POPUP_DISMISS:
                //msg = Message.obtain();
                msg.arg1 = 4;
                msg.arg2 = 5;
                msg.what = ConstantUtil.POPUP_DISMISS;
                QuickPopup quickPopup1 = (QuickPopup) msg.obj;
                quickPopup1.dismiss();
        }
    }


}
