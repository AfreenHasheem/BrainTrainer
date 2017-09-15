package com.example.afreen.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.afreen.braintrainer.R.id.pointsTextView;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    TextView resultTextView;
    int numberOfQuestions=0;
    TextView pointsTextView;
    Button button0, button1, button2, button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view)
    {
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000)
        {


            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultTextView.setText("Your score: " + Integer.toString(score) +  "/" + Integer.toString(numberOfQuestions));

            }
        }.start();

    }

    public void generateQuestion(){

        Random random= new Random();//to generate random numbers
        int a=random.nextInt(99);
        int b=random.nextInt(99);

        sumTextView.setText(Integer.toString(a) +  "+" + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);//positions 0,1,2,3

        answers.clear();//to clear the array list and get a new question with correct answer

        int incorrectAnswer;
        for (int i=0; i<4 ;i++){
            if (i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{

                incorrectAnswer=random.nextInt(199);
                while(incorrectAnswer==a+b){
                    incorrectAnswer=random.nextInt(4);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct");
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) +  "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
         sumTextView= (TextView) findViewById(R.id.sumTextView);
         button0= (Button) findViewById(R.id.button0);
         button1= (Button) findViewById(R.id.button1);
         button2= (Button) findViewById(R.id.button2);
         button3= (Button) findViewById(R.id.button3);

        resultTextView= (TextView) findViewById(R.id.resultTextView);
        pointsTextView= (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout= (RelativeLayout) findViewById(R.id.gameRelativelayout);





    }
}
