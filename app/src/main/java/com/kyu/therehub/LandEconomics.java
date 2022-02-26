package com.kyu.therehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.WindowManager;

public class LandEconomics extends AppCompatActivity {
    private CardView card1,card2,card3,card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_land_economics);
        getSupportActionBar().hide();
        initials();
    }

    private void initials() {
        card1=findViewById(R.id.card1);//Reports instatiate
        card2=findViewById(R.id.card2);//journals instatiate
        card3=findViewById(R.id.card3);//proposals instatiation
        card4=findViewById(R.id.card4);//notes instatiation
    }
}