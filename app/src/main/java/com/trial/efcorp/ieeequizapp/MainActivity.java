package com.trial.efcorp.ieeequizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] questions,choice1,choice2,choice3,choice4,answer;
    String filename1, filename2, filename3, filename4,filename5,filename6;
    Button quizBtn;

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

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);

            }
        });


    }

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

