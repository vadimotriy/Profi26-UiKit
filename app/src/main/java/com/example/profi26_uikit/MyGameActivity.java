package com.example.profi26_uikit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentation.GameVIewModel;

import java.util.ArrayList;
import java.util.Collections;

public class MyGameActivity extends AppCompatActivity {

    private ImageView[][] tiles = new ImageView[3][3];
    private ImageView tileChecked = null;
    private ArrayList<Bitmap> originalBitmaps = new ArrayList<>();
    GridLayout grid;
    int tileSize;

    GameVIewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game);

        grid = findViewById(R.id.gridLayout);

        Bitmap original = BitmapFactory.decodeResource(getResources(), R.drawable.image_puzzle);
        int screenSize = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels) - 200;
        Bitmap scaled = Bitmap.createScaledBitmap(original, screenSize, screenSize, true);

        tileSize = scaled.getWidth() / 3;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Bitmap tile = Bitmap.createBitmap(scaled, j * tileSize, i * tileSize, tileSize, tileSize);
                originalBitmaps.add(tile);
            }
        }

        ArrayList<Bitmap> shuffledBitmaps = new ArrayList<>(originalBitmaps);
        Collections.shuffle(shuffledBitmaps);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ImageView iv = new ImageView(this);
                iv.setImageBitmap(shuffledBitmaps.get(i * 3 + j));
                iv.setScaleType(ImageView.ScaleType.FIT_XY);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = tileSize; params.height = tileSize;
                params.setMargins(2, 2, 2, 2);
                iv.setLayoutParams(params);

                iv.setOnTouchListener(new TileTouchListener());
                grid.addView(iv);
                tiles[i][j] = iv;
            }
        }


        model = new ViewModelProvider(this).get(GameVIewModel.class);
        model.getName("vblinov2009.rus@yandex.ru", "12345678").observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MyGameActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private class TileTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView tileTouched = (ImageView) v;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    tileChecked = tileTouched;
                    tileTouched.setAlpha(0.6f);
                    return true;

                case MotionEvent.ACTION_UP:
                    tileTouched.setAlpha(1.0f);

                    int[] location = new int[2];
                    tileTouched.getLocationOnScreen(location);
                    float touchX = event.getRawX(), touchY = event.getRawY();

                    for (int  i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            ImageView tileTarget = tiles[i][j];

                            if (tileTarget == tileTouched) continue;
                            tileTarget.getLocationOnScreen(location);

                            int tileLeft = location[0], tileTop = location[1];
                            int tileRight = tileLeft + tileSize, tileBottom = tileTop + tileSize;

                            if (touchX >= tileLeft && touchX <= tileRight && touchY >= tileTop && touchY <= tileBottom) {
                                swapTiles(tileTouched, tileTarget);
                                return true;
                            }
                        }
                    }

                    tileChecked = null;
                    return true;
            }

            return false;
        }
    }

    private void swapTiles(ImageView tile1, ImageView tile2) {
        Bitmap bitmap1 = ((BitmapDrawable) tile1.getDrawable()).getBitmap();
        Bitmap bitmap2 = ((BitmapDrawable) tile2.getDrawable()).getBitmap();

        tile1.setImageBitmap(bitmap2);
        tile2.setImageBitmap(bitmap1);
    }
}