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
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * TODO: document your custom view class.
 */
public class UKInputField extends AppCompatEditText {

    public UKInputField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setBackgroundResource(R.drawable.layer_input);

        setHint("Full Name");
        setHintTextColor(Color.BLACK); setTextColor(Color.BLACK);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        setMinWidth((int) (260 * getResources().getDisplayMetrics().density));

        setSingleLine(true);
        setMaxLines(1);
        int size = (int) (8 * getResources().getDisplayMetrics().density);
        setPadding(0, size, 0, size);

    }
}