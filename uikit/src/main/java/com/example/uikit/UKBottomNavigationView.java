package com.example.uikit;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UKBottomNavigationView extends LinearLayout {
    private OnListener listener;

    public UKBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        for (int i = 0; i < 5; ++i) {
            TextView tv = new TextView(context);

            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
            tv.setLayoutParams(params);

            int finalI = i;
            tv.setOnClickListener(v -> setSelectedTab(finalI));

            addView(tv);
        }

        setSelectedTab(0);
        setBackgroundResource(R.drawable.b);
    }

    public void setSelectedTab(int index) {
        if (listener != null) listener.result(index);
    }

    public void setOnListener(OnListener listener) {
        this.listener = listener;
    }

    public interface OnListener {
        void result(int index);
    }
}