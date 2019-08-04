package com.fungames.galaxyshooter.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.PopupWindow;

import com.fungames.galaxyshooter.constant.ConstantUtil;
import com.fungames.galaxyshooter.view.BaseView;
import com.fungames.galaxyshooter.view.MainView;
import com.fungames.galaxyshooter.view.ReadyView;

import java.lang.ref.WeakReference;

/**
 * Created by daipeng on 2019/7/11.
 */

public class CountDownHandler extends Handler {

    private final String TAG = CountDownHandler.class.getSimpleName();

    public final WeakReference<BaseView> weakReference;

    public CountDownHandler(BaseView baseView) {
        weakReference = new WeakReference<>(baseView);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case ConstantUtil.RESURRECTION_COUNT:
                MainView mainView = (MainView) weakReference.get();
                CountDownHandlerObject object = (CountDownHandlerObject) msg.obj;
                int timer = object.getInitTimer();
                mainView.getCountView().setText(String.valueOf(timer / 1000));
                msg = Message.obtain();//重新获取消息
                msg.arg1 = 0;
                msg.arg2 = 1;
                msg.what = ConstantUtil.RESURRECTION_COUNT;
                object.setInitTimer(timer -= 1000);
                msg.obj = object;
                PopupWindow popupWindow = object.getPopupWindow();
                if (timer >= 0) {
                    sendMessageDelayed(msg, ConstantUtil.DELAY_SEND_DURATION);
                }else{
                    popupWindow.dismiss();
                    mainView.getCountView().setText("5");

                    //返回游戏重新开始
                    Message message = Message.obtain();
                    message.what = ConstantUtil.TO_END_VIEW;
                    message.arg1 = Integer.valueOf(mainView.getSumScore());
                    mainView.getMainActivity().getHandler().sendMessage(message);
                }
                break;
            case ConstantUtil.RUNNING_GAME:
                removeMessages(ConstantUtil.RUNNING_GAME);
                removeMessages(ConstantUtil.READY_GAME);
                final MainView mainView1 = (MainView) weakReference.get();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mainView1.isPlay){
                            mainView1.initObject();
                            mainView1.drawSelf();
                            mainView1.viewLogic();
                            if (!mainView1.mMediaPlayer.isPlaying()) {
                                mainView1.mMediaPlayer.start();
                            }
                            postDelayed(this, 60);
                        }else{
                            mainView1.mMediaPlayer.pause();// 音乐暂停
                        }
                    }
                }, 60);
                break;
            case ConstantUtil.POPUP_DISMISS:
                MainView mainView2 = (MainView) weakReference.get();
                msg.arg1 = 4;
                msg.arg2 = 5;
                CountDownHandlerObject object1 = (CountDownHandlerObject) msg.obj;
                PopupWindow popupWindow2 = object1.getPopupWindow();
                popupWindow2.dismiss();
                mainView2.adsRewardedVideoClosedHandler(false);
                break;
            case ConstantUtil.READY_GAME:
                removeMessages(ConstantUtil.READY_GAME);
                final ReadyView readyView = (ReadyView) weakReference.get();
                msg.arg1 = 6;
                msg.arg2 = 7;
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(readyView.showReady){
                            readyView.drawSelf();
                            postDelayed(this, 300);
                        }
                    }
                }, 300);

                break;
        }
    }


}
