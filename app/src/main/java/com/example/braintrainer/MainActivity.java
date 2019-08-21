package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultTextView;
    TextView scoreTextView;
    TextView prodTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score = 0;
    int noOfQuestions = 0;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        score = 0;
        noOfQuestions = 0;

        timerTextView.setText("30s");

        newQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

        playAgainButton.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");

    }

    public void chooseAnswer(View view){
        String chosen = view.getTag().toString();
        noOfQuestions++;
        if(Integer.valueOf(chosen) == locationOfCorrectAnswer){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        scoreTextView.setText(score + "/" + noOfQuestions);
        newQuestion();
    }

    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        prodTextView.setText(a + " X " + b);

        for(int i=0;i<4;i++){
            if(i == locationOfCorrectAnswer)
                answers.add(a*b);
            else{
                int wrongAnswer = random.nextInt(a*b)+1;
                while(wrongAnswer == a*b || answers.contains(wrongAnswer))
                    wrongAnswer = random.nextInt(a*b)+1;
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.yscoreTextView);
        prodTextView = findViewById(R.id.prodTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
