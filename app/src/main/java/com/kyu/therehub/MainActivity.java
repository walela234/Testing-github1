package com.kyu.therehub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation top, bottom;
    private TextView app_name, app_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        app_name = findViewById(R.id.app_name);
        app_quote = findViewById(R.id.app_quote);
        top = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        app_name.setAnimation(top);
        app_quote.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Choose_you.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);


    }
}

