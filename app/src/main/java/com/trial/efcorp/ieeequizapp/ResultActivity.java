package com.trial.efcorp.ieeequizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {

    TextView scoreView,timeView;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;

    private ProgressDialog mProgress;

    int score;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mProgress = new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();

        mProgress.setMessage("Uploading Your Score");
        mProgress.show();
        mProgress.setCancelable(false);

        Intent intent = getIntent();
        Bundle exBundle= getIntent().getExtras();
        score= exBundle.getInt("score");
        time = intent.getStringExtra("time");
        scoreView = findViewById(R.id.ScoreView);
        timeView = findViewById(R.id.TimeView);
        scoreView.setText("Score: "+score);
        timeView.setText("Time: "+time);


        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Users");

        final String user_id = mAuth.getCurrentUser().getUid();

        mDatabaseUsers.child(user_id).child("score").setValue(score);
        mDatabaseUsers.child(user_id).child("time").setValue(time);

        mProgress.dismiss();






    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


}
