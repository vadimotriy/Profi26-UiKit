package com.example.uikit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

public class UKBottomNavigationView extends LinearLayout {

    public UKBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.parseColor("#FF4D7A"));
        setGravity(Gravity.CENTER_VERTICAL);

        int padding = (int) (8 * getResources().getDisplayMetrics().density);
        setPadding(padding, padding, padding, padding);

        addNavigationItem(context, "Statistics", R.drawable.statistics_icon);
        addNavigationItem(context, "Discover", R.drawable.location_pin_icon);
        addCenterItem(context);
        addNavigationItem(context, "Chat", R.drawable.chat_icon);
        addNavigationItem(context, "Profile", R.drawable.profile_icon);
    }

    private void addNavigationItem(Context context, String label, int iconResId) {
        LinearLayout itemLayout = new LinearLayout(context);
        itemLayout.setOrientation(VERTICAL);
        itemLayout.setGravity(Gravity.CENTER);
        itemLayout.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));

        ImageView icon = new AppCompatImageView(context);
        icon.setImageResource(iconResId);
        icon.setColorFilter(Color.WHITE);
        int iconSize = (int) (24 * getResources().getDisplayMetrics().density);
        icon.setLayoutParams(new LayoutParams(iconSize, iconSize));

        TextView text = new AppCompatTextView(context);
        text.setText(label);
        text.setTextColor(Color.WHITE);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        text.setGravity(Gravity.CENTER);

        itemLayout.addView(icon);
        itemLayout.addView(text);
        addView(itemLayout);
    }

    private void addCenterItem(Context context) {
        LinearLayout itemLayout = new LinearLayout(context);
        itemLayout.setOrientation(VERTICAL);
        itemLayout.setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.2f);
        itemLayout.setLayoutParams(params);

        Drawable circleBg = ContextCompat.getDrawable(context, R.drawable.base_ellipse);

        ImageView icon = new AppCompatImageView(context);
        icon.setImageResource(R.drawable.schedule_icon);
        icon.setColorFilter(Color.parseColor("#FF4D7A"));
        int iconSize = (int) (32 * getResources().getDisplayMetrics().density);
        icon.setLayoutParams(new LayoutParams(iconSize, iconSize));
        icon.setBackground(circleBg);
        int padding = (int) (8 * getResources().getDisplayMetrics().density);
        icon.setPadding(padding, padding, padding, padding);

        itemLayout.addView(icon);
        addView(itemLayout);
    }
}