package com.trial.efcorp.ieeequizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    private TextView timeVal;
    Handler customHandler = new Handler();
    long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,updatetime=0L;
    private TextView mQuestion;
    private Button mChoice1,mChoice2,mChoice3,mChoice4;

    int Score,i;

    String[] questions,choice1,choice2,choice3,choice4,answer;
    String filename1, filename2, filename3, filename4,filename5,filename6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        filename1="questions";
        filename2="choice1";
        filename3="choice2";
        filename4="choice3";
        filename5="choice4";
        filename6="answer";

        loadfromfile();

        mQuestion = findViewById(R.id.questionField);
        mChoice1 = findViewById(R.id.choice1);
        mChoice2 = findViewById(R.id.choice2);
        mChoice3 = findViewById(R.id.choice3);
        mChoice4 = findViewById(R.id.choice4);

        startTime = SystemClock.uptimeMillis();
        timeVal = findViewById(R.id.projtime);
        customHandler.postDelayed(updateTimeThread,0);

                i=0;
        updatequiz();

    }

    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-startTime;
            updatetime= timeSwapBuff+timeInMilliseconds;
            int secs = (int)(updatetime/1000);
            int mins = secs/60;
            secs%=60;
            int milliseconds= (int)updatetime%1000;
            timeVal.setText(""+mins+":"+String.format("%2d",secs)+":"+String.format("%3d",milliseconds));
            customHandler.postDelayed(this,0);
        }
    };

    private void updatequiz(){

        if(i>1){

            Intent resultIntent = new Intent(getApplicationContext(),ResultActivity.class);
            resultIntent.putExtra("time",timeVal.getText().toString());
            resultIntent.putExtra("score",Score);
            startActivity(resultIntent);
            finish();
        }else {

            mQuestion.setText(questions[i]);
            mChoice1.setText(choice1[i]);
            mChoice2.setText(choice2[i]);
            mChoice3.setText(choice3[i]);
            mChoice4.setText(choice4[i]);

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mChoice1.getText().toString().equals(answer[i])) {
                        Score+=1;
                    }
                    i += 1;
                    updatequiz();
                }
            });


            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (mChoice2.getText().toString().equals(answer[i])) {
                        Score+=1;
                    }

                    i += 1;
                    updatequiz();

                }
            });

            mChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mChoice3.getText().toString().equals(answer[i])) {
                        Score+=1;

                    }
                    i += 1;
                    updatequiz();

                }
            });

            mChoice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mChoice4.getText().toString().equals(answer[i])) {
                        Score+=1;

                    }

                    i += 1;
                    updatequiz();
                }
            });


        }
    }

    @Override
    public void onBackPressed() {

        final Dialog dialog= new Dialog(QuizActivity.this);
        dialog.setContentView(R.layout.alert_layout);
        TextView title = dialog.findViewById(R.id.title);
        TextView message = dialog.findViewById(R.id.message);
        Button okBtn = dialog.findViewById(R.id.yes);
        Button noBtn = dialog.findViewById(R.id.no);

        title.setText("Warning");
        message.setText("Do you want to quit? \r\n" +
                "All your progress will be lost! ");

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }

    private void loadfromfile() {

        FileInputStream fis1;
        try
        {
            fis1 = openFileInput(filename1);
            ObjectInputStream ois = new ObjectInputStream(fis1);
            questions =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        FileInputStream fis2;
        try
        {
            fis2 = openFileInput(filename2);
            ObjectInputStream ois = new ObjectInputStream(fis2);
            choice1 =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        FileInputStream fis3;
        try
        {
            fis3 = openFileInput(filename3);
            ObjectInputStream ois = new ObjectInputStream(fis3);
            choice2 =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        FileInputStream fis4;
        try
        {
            fis4 = openFileInput(filename4);
            ObjectInputStream ois = new ObjectInputStream(fis4);
            choice3 =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        FileInputStream fis5;
        try
        {
            fis5 = openFileInput(filename5);
            ObjectInputStream ois = new ObjectInputStream(fis5);
            choice4 =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        FileInputStream fis6;
        try
        {
            fis6 = openFileInput(filename6);
            ObjectInputStream ois = new ObjectInputStream(fis6);
            answer =(String[]) ois.readObject();
            ois.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
