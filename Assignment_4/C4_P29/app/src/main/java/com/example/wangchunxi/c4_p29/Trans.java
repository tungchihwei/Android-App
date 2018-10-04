package com.example.wangchunxi.c4_p29;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Trans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String label = intent.getStringExtra("label");

        TextView tran_label = findViewById(R.id.trans_label);
        tran_label.setText(label);
        TextView tran_txt = findViewById(R.id.trans_txt);
        tran_txt.setText(text);
    }

    public void goBack(View v){
        this.finish();
    }
}
