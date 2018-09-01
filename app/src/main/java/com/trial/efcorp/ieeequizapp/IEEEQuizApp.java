package com.trial.efcorp.ieeequizapp;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class IEEEQuizApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
