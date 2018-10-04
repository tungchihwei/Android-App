package com.example.wangchunxi.c4_p29;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    String curLabel = "Chinese";
    String curText = "你好，世界";
    private RadioButton chinese;
    private RadioButton spanish;
    private RadioButton german;
    private RadioButton french;
    private RadioButton latin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chinese = findViewById(R.id.rb_Chinese);
        spanish = findViewById(R.id.rb_Spanish);
        german = findViewById(R.id.rb_German);
        french = findViewById(R.id.rb_French);
        latin = findViewById(R.id.rb_Latin);

        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curLabel = "Chinese";
                curText = "你好，世界";
            }
        });

        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curLabel = "Spanish";
                curText = "Hola Mundo";
            }
        });

        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curLabel = "German";
                curText = "Hallo Welt";
            }
        });

        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curLabel = "French";
                curText = "Bonjour le monde";
            }
        });

        latin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curLabel = "Latin";
                curText = "salve Orbis Terrarum";
            }
        });

    }


    public void goToTranslation(View v) {
        Intent intent = new Intent(this, Trans.class);
        intent.putExtra("label",curLabel);
        intent.putExtra("text",curText);
//        Bundle bd = new Bundle();
//        bd.putString("label", curLabel);
//        bd.putString("text", curText);
//        intent.putExtras(bd);
        Log.i("EEEEE", "main");
        startActivity(intent);
    }
}
