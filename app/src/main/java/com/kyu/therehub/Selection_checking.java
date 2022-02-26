package com.kyu.therehub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;



public class Selection_checking extends AppCompatActivity {
    private CardView cardONE,cardTWO,cardTHREE,cardFOUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selection_checking);
        getSupportActionBar().hide();
        initialisation();
        cardONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewReport.class));
            }
        });


    }

    private void initialisation() {
        cardONE=findViewById(R.id.cardONE);
        cardTWO=findViewById(R.id.cardTWO);
        cardTHREE=findViewById(R.id.cardTHREE);
        cardFOUR=findViewById(R.id.cardFOUR);
    }
}