package com.example.tourloginsignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tourist_dashboard extends AppCompatActivity {


    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_dashboard);
    cv=findViewById(R.id.Cv);

    cv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent= new Intent(Tourist_dashboard.this,Chat.class);
            startActivity(intent);
        }
    });

    }
}
