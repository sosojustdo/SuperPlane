package com.fungames.galaxyshooter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.widget.QuickPopup;

/**
 * Created by daipeng on 2019/7/21.
 */

public class EmptyActivity extends Activity {

    private static final int EMPTY_ACTIVITY_WHAT = 111111;

    private QuickPopup quickPopup;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == EMPTY_ACTIVITY_WHAT) {
                QuickPopup quickPopup1 = (QuickPopup) msg.obj;
                quickPopup1.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        TextView textView = this.findViewById(R.id.test);
        textView.setClickable(true);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quickPopup = QuickPopupBuilder.with(getBaseContext()).contentView(R.layout.popup_normal)
                        .config(new QuickPopupConfig().withClick(R.id.tv_test, new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                //quickPopup.dismiss();

                                //handler 出发dismiss
                                Message msg = new Message();
                                msg.what = EMPTY_ACTIVITY_WHAT;
                                msg.arg1 = 0;
                                msg.arg2 = 1;
                                msg.obj = quickPopup;
                                handler.sendMessage(msg);
                            }
                        }, true)).build();

                //设置是否允许点击BasePopup外部时触发dismiss
                quickPopup.setOutSideDismiss(false);
                //设置BasePopup是否允许返回键dismiss
                quickPopup.setBackPressEnable(false);

                //展示PopupWindow
                quickPopup.showPopupWindow();
            }
        });
    }
}
