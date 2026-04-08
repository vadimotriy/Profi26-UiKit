package com.example.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class UKButton extends AppCompatButton {

    public UKButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UKButton);

        Drawable icon = androidx.core.content.ContextCompat.getDrawable(context, R.drawable.img);
        int size = (int) (18 * getResources().getDisplayMetrics().density);
        icon.setBounds(0, 0, size, size);

        try {
            int buttonType = typedArray.getInt(R.styleable.UKButton_buttonType, 0);
            switch (buttonType) {
                case 1:
                    setBackgroundResource(R.drawable.shape_button);
                    setText("Let’s Combat!");
                    setTextColor(Color.WHITE);
                    setPadding(60, 18, 60, 18);
                    setGravity(Gravity.CENTER_VERTICAL); break;
                case 2:
                    setBackgroundColor(Color.TRANSPARENT);
                    setText("Logout");
                    setCompoundDrawables(icon, null, null, null);
                    setCompoundDrawablePadding(16);
                    setTextColor(Color.BLACK);
                    setGravity(Gravity.CENTER_VERTICAL); break;
                case 3:
                    setBackgroundColor(Color.TRANSPARENT);
                    setText("Logout");
                    setCompoundDrawables(null, null, null, icon);
                    setCompoundDrawablePadding(16);
                    setTextColor(Color.BLACK);
                    setGravity(Gravity.CENTER_HORIZONTAL); break;
                case 4:
                    setBackgroundColor(Color.TRANSPARENT);
                    setCompoundDrawables(icon, null, null, null);
                    setMinWidth(0); setMinimumWidth(0);
                    setMinHeight(0); setMinimumHeight(0);
                    setGravity(Gravity.CENTER); break;
            };
        } finally {
            typedArray.recycle();
        }

        setAllCaps(false);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }
}