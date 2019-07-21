package com.fungames.galaxyshooter.test;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fungames.galaxyshooter.R;

/**
 * Created by daipeng on 2019/7/21.
 */

public class PopupWindowActivity extends Activity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        TextView textView = this.findViewById(R.id.test);
        textView.setClickable(true);

        try{
            final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.popup_normal, null, false);
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

            popupWindow.setFocusable(false);
            popupWindow.setOutsideTouchable(false);

            popupWindow.setTouchable(true);
            popupWindow.setTouchInterceptor(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                    // 这里如果返回true的话，touch事件将被拦截
                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                }
            });
            popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
