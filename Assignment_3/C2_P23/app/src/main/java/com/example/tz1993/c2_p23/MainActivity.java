package com.example.tz1993.c2_p23;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView redtxt;
    private TextView yellowtxt;
    private TextView grntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        startActivity(intent);

        btn=(Button)findViewById(R.id.btn);
        redtxt=(TextView)findViewById(R.id.redtxt);
        yellowtxt=(TextView)findViewById(R.id.yellowtxt);
        grntxt=(TextView)findViewById(R.id.grntxt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (redtxt.getCurrentTextColor() == Color.RED) {
                    grntxt.setTextColor(Color.GREEN);
                    grntxt.setTextSize(40);
                    redtxt.setTextSize(12);
                    redtxt.setTextColor(Color.GRAY);
                } else if (grntxt.getCurrentTextColor() == Color.GREEN) {
                    yellowtxt.setTextColor(Color.YELLOW);
                    yellowtxt.setTextSize(40);
                    grntxt.setTextSize(12);
                    grntxt.setTextColor(Color.GRAY);
                } else if (yellowtxt.getCurrentTextColor() == Color.YELLOW) {
                    yellowtxt.setTextColor(Color.GRAY);
                    yellowtxt.setTextSize(12);
                    redtxt.setTextSize(40);
                    redtxt.setTextColor(Color.RED);
                }
            }
        });

    }
}
