package com.fungames.galaxyshooter.ads.listener;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by daipeng on 2019/7/20.
 */

public class BaseTouchListener implements View.OnTouchListener {

    private static int timeout=200;//双击间四百毫秒延时
    private int clickCount = 0;//记录连续点击次数
    private Handler handler = new Handler();
    private MyClickCallBack myClickCallBack;

    public interface MyClickCallBack{
        void oneClick();//点击一次的回调
        void doubleClick();//连续点击两次的回调

    }

    public BaseTouchListener(MyClickCallBack myClickCallBack) {
        this.myClickCallBack = myClickCallBack;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickCount++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (clickCount == 1) {
                        myClickCallBack.oneClick();
                    }else if(clickCount==2){
                        myClickCallBack.doubleClick();
                    }
                    handler.removeCallbacksAndMessages(null);
                    //清空handler延时，并防内存泄漏
                    clickCount = 0;//计数清零
                }
            },timeout);//延时timeout后执行run方法中的代码
        }
        return false;//让点击事件继续传播，方便再给View添加其他事件监听
    }

}
