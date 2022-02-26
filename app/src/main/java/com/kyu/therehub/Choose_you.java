package com.kyu.therehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class Choose_you extends AppCompatActivity {
    private CardView card1, card2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_you);
        getSupportActionBar().hide();

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register_Lecturer.class));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Selection_checking.class));
            }
        });




    }


}