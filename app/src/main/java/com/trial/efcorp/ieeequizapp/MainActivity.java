package com.trial.efcorp.ieeequizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String[] questions,choice1,choice2,choice3,choice4,answer;
    String filename1, filename2, filename3, filename4,filename5,filename6;
    Button quizBtn;
    Handler quizHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizBtn = findViewById(R.id.quizBtn);

        questions  = new String[] { "First man on moon?",
                "First man on space?" };

        choice1  = new String[] { "Edwin Aldrin",
                "Laika" };

        choice2  = new String[] { "Neil Armstrong",
                "Valentina Tereshkova" };

        choice3  = new String[] { "Aldrin Buzz",
                "Yuri Gagarin" };

        choice4  = new String[] { "Daniel Mcolin",
                "Sputnik" };

        answer  = new String[] { "Neil Armstrong",
                "Yuri Gagarin" };

        filename1="questions";
        filename2="choice1";
        filename3="choice2";
        filename4="choice3";
        filename5="choice4";
        filename6="answer";

        saveToFile();

        quizHandler.postDelayed(updateTimeThread,0);

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();

                int dayofyear = cal.get(Calendar.DAY_OF_YEAR);
                int year = cal.get(Calendar.YEAR);
                int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
                int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);

                /*if (hour==21&&min>=0&&min<=2)
                {
                    Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                    startActivity(intent);
                }else {

                    Toast.makeText(MainActivity.this, "Quiz will begin in "+ (21-hour) + "hour" + (60-min) + "mins", Toast.LENGTH_LONG).show();
                }*/

                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);

            }
        });


    }

    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            Calendar cal = Calendar.getInstance();

            int dayofyear = cal.get(Calendar.DAY_OF_YEAR);
            int year = cal.get(Calendar.YEAR);
            int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
            int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

            if(hour==21&&min==38&&sec==0){

                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);

            }

            quizHandler.postDelayed(this,0);
        }
    };

    private void saveToFile() {
        FileOutputStream fos1;
        try {
            fos1 = getApplicationContext().openFileOutput(filename1, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos1);
            oos.writeObject(questions);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileOutputStream fos2;
        try {
            fos2 = getApplicationContext().openFileOutput(filename2, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos2);
            oos.writeObject(choice1);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileOutputStream fos3;
        try {
            fos3 = getApplicationContext().openFileOutput(filename3, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos3);
            oos.writeObject(choice2);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileOutputStream fos4;
        try {
            fos4 = getApplicationContext().openFileOutput(filename4, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos4);
            oos.writeObject(choice3);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        FileOutputStream fos5;
        try {
            fos5 = getApplicationContext().openFileOutput(filename5, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos5);
            oos.writeObject(choice4);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileOutputStream fos6;
        try {
            fos6 = getApplicationContext().openFileOutput(filename6, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos6);
            oos.writeObject(answer);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

