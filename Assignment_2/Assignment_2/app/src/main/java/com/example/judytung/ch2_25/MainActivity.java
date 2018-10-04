package com.example.judytung.ch2_25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_enterpass;
    private TextView txt_check;
    private EditText edt_pass1, edt_pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_enterpass = (Button) findViewById(R.id.btn_pass_enter);
        txt_check = (TextView) findViewById(R.id.txt_check);
        edt_pass1 = (EditText) findViewById(R.id.edt_pass_fst);
        edt_pass2 = (EditText) findViewById(R.id.edt_pass_sec);

        btn_enterpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = edt_pass1.getText().toString();
                String pass2 = edt_pass2.getText().toString();

                if (pass1.equals(pass2))
                   txt_check.setText("THANK YOU!");
                else
                    txt_check.setText("PASSWORD MUST MATCH!");
            }
        });
    }
}
