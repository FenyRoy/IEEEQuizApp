package com.trial.efcorp.ieeequizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView scoreView,timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        Bundle exBundle= getIntent().getExtras();
        int score= exBundle.getInt("score");
        String time = intent.getStringExtra("time");
        scoreView = findViewById(R.id.ScoreView);
        timeView = findViewById(R.id.TimeView);
        scoreView.setText("Score: "+score);
        timeView.setText("Time: "+time);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


}
