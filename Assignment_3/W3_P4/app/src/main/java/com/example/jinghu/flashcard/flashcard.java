package com.example.jinghu.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class flashcard extends AppCompatActivity {

    private int numQuestion = 0;
    private int correct = 0;

    Random random = new Random();
    TextView numerator_text;
    TextView denominator_text;
    TextView questionText;
    EditText answer;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        numerator_text = findViewById(R.id.numerator);
        denominator_text = findViewById(R.id.denominator);
        questionText = findViewById(R.id.test_question);
        answer = findViewById(R.id.edit_answer);
        submit_btn = findViewById(R.id.submit_button);
        ((TextView) findViewById(R.id.equals)).setText("=");

        if (savedInstanceState != null) {
            numerator_text.setText(savedInstanceState.getString("numerator", ""));
            denominator_text.setText(savedInstanceState.getString("denominator", ""));
            numQuestion = savedInstanceState.getInt("qNum", 0);
            correct = savedInstanceState.getInt("correct", 0);
            submit_btn.setEnabled(savedInstanceState.getBoolean("submitEnabled", true));
            setQuestionText();
        } else {
            Intent intent = getIntent();
            Toast toast = Toast.makeText(this, String.format("Welcome %s!", intent.getStringExtra(Login.USERNAME)), Toast.LENGTH_LONG);
            toast.show();
            generate();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("numerator", numerator_text.getText().toString());
        outState.putString("denominator", denominator_text.getText().toString());
        outState.putInt("qNum", numQuestion);
        outState.putInt("correct", correct);
        outState.putBoolean("submitEnabled", submit_btn.isEnabled());
        super.onSaveInstanceState(outState);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences.Editor ed = prefs.edit();
//        ed.putString("numerator", numerator_text.getText().toString());
//        ed.putString("denominator", denominator_text.getText().toString());
//        ed.putInt("qNum", numQuestion);
//        ed.putInt("correct", correct);
//        ed.commit();
//    }

    public void setQuestionText() {
        questionText.setText(String.format("Question %s - Correct %s", numQuestion+"", correct+""));
    }

    public void generate() {
        answer.setText("");
        numQuestion += 1;
        setQuestionText();
        int numerator = random.nextInt(1001);
        int denominator;
        do {
            denominator = random.nextInt(1000) + 1;
        } while (numerator % denominator != 0);

        numerator_text.setText(""+numerator);
        denominator_text.setText("÷ " + denominator);
    }

    public void submit(View view) {
        String answer_text = answer.getText().toString();
        if (answer_text != "") {
            try {
                int answer_int = Integer.parseInt(answer_text);
                int numerator = Integer.parseInt(numerator_text.getText().toString());
                String denominator_string = denominator_text.getText().toString();
                int denominator = Integer.parseInt(denominator_string.substring(2,denominator_string.length()));
                if (answer_int == numerator / denominator) {
                    correct += 1;
                }

                if (numQuestion == 10) {
                    setQuestionText();
                    submit_btn.setEnabled(false);
                    Toast toast = Toast.makeText(this, String.format("Congrats! You got %s out of %s correct!", correct+"", numQuestion+""), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    generate();
                }
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "Please input a number!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, "Please input an answer!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void reset(View view) {
        numQuestion = 0;
        correct = 0;

        if (!submit_btn.isEnabled()) {
            submit_btn.setEnabled(true);
        }

        generate();
    }

}
