package com.example.profi26_uikit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uikit.UKBottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(this, MyGameActivity.class));

        UKBottomNavigationView nav = findViewById(R.id.bottom_nav);

        // Обработка переключения вкладок
        nav.setOnListener(index -> {
            switch (index) {
                case 0:
                    Toast.makeText(this, "0", Toast.LENGTH_SHORT).show(); break;
                case 1:
                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show(); break;
                case 2:
                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show(); break;
                case 3:
                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show(); break;
                case 4:
                    Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();  break;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}