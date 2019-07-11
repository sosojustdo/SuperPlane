package com.hurteng.stormplane.handler;

import android.os.Handler;
import android.os.Message;

import com.hurteng.stormplane.constant.ConstantUtil;
import com.hurteng.stormplane.view.MainView;

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
                if (timer > 0) {
                    sendMessageDelayed(msg, ConstantUtil.DELAY_SEND_DURATION);
                }else{
                    QuickPopup quickPopup = object.getQuickPopup();
                    quickPopup.dismiss();

                    //返回游戏重新开始
                    Message message = new Message();
                    message.what = ConstantUtil.TO_END_VIEW;
                    message.arg1 = Integer.valueOf(mainView.getSumScore());
                    mainView.getMainActivity().getHandler().sendMessage(message);
                }
                break;
        }
    }


}
