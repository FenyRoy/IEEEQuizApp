package com.trial.efcorp.ieeequizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetupActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mGuildNumFied;
    private Button mSubmitBtn;


    private ProgressDialog mProgress;


    private FirebaseAuth mAuth;

    private DatabaseReference mDatabaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        mAuth= FirebaseAuth.getInstance();

        mDatabaseUsers= FirebaseDatabase.getInstance().getReference().child("Users");


        mNameField= (EditText)findViewById(R.id.setupnameField);
        mGuildNumFied= (EditText)findViewById(R.id.guildNumField);
        mSubmitBtn=(Button)findViewById(R.id.setupFinishBtn);

        mProgress = new ProgressDialog(this);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSetupAccout();

            }
        });


    }

    private void startSetupAccout() {

        mProgress.setMessage("Uploading....");
        mProgress.setCancelable(false);
        mProgress.show();
        final String name= mNameField.getText().toString().trim();
        final String guildNum = mGuildNumFied.getText().toString();

        final String user_id = mAuth.getCurrentUser().getUid();

        if(!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(guildNum) ){

            mDatabaseUsers.child(user_id).child("name").setValue(name);
            mDatabaseUsers.child(user_id).child("guild").setValue(guildNum);
            mProgress.dismiss();
            Intent mainIntent = new Intent(SetupActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Fields Empty",Toast.LENGTH_LONG).show();
            mProgress.dismiss();
        }

    }


    public void onBackPressed(){
        finish();
        moveTaskToBack(true);
    }
}

