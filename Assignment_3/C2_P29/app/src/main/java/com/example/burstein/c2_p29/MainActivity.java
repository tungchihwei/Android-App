package com.example.burstein.c2_p29;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText edt1;
    private EditText edt2;
    private EditText edt3;
    private TextView txtResult;

    private int calPerServing1 = 10;
    private int calPerServing2 = 100;
    private int calPerServing3 = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);

        TextChangeHandler tch = new TextChangeHandler();
        edt1.addTextChangedListener(tch);
        edt2.addTextChangedListener(tch);
        edt3.addTextChangedListener(tch);
    }

    public void calculate() {
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        txtResult = (TextView) findViewById(R.id.txtResult);

        try{
            int num1 = edt1.getText().toString().equals("") ? 0 : Integer.parseInt(edt1.getText().toString());
            int num2 = edt2.getText().toString().equals("") ? 0 : Integer.parseInt(edt2.getText().toString());
            int num3 = edt3.getText().toString().equals("") ? 0 : Integer.parseInt(edt3.getText().toString());
            int total = num1*calPerServing1 + num2*calPerServing2 + num3*calPerServing3;
            txtResult.setText(Integer.toString(total));
        } catch (Exception e) {
            Log.i("exception", "calculate");
        }
    }

    private class TextChangeHandler implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }
}
