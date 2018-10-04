package com.example.wangchunxi.w3_p2;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TextView[][] label;
    private EditText input;
    private TextView percent;
    private SeekBar seekBar;
    private int cols = 2;
    private int rows = 2;
    private double txt = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        buildGuiByCode();
    }

    public void buildGuiByCode() {
        // get width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / 5;

        // create the layout manager as a GridLayout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(cols);
        gridLayout.setRowCount(rows);

        // create input textView
        input = new EditText(this);
        GridLayout.Spec rowSpec = GridLayout.spec(0,1);
        GridLayout.Spec colSpec = GridLayout.spec(0, cols);
        GridLayout.LayoutParams lpInput = new GridLayout.LayoutParams(rowSpec, colSpec);
        input.setLayoutParams(lpInput);

        // set up input's characteristics
        input.setWidth(5 * w);
        input.setHeight(w);
        input.setGravity(Gravity.LEFT);
        input.setPadding(50,0,0,0);
        input.setBackgroundColor(Color.rgb(135,206,250));
        input.setTextSize((int)(w * 0.15));
        input.setHint("Enter Amount");
        input.setGravity(Gravity.CENTER_VERTICAL);

        gridLayout.addView(input);

        // create the seekbar to gridLayout
        percent = new TextView(this);
        percent.setText("0%");
        percent.setTextColor(Color.BLACK);
        percent.setGravity(Gravity.CENTER);
        gridLayout.addView(percent, w, w / 2);

        seekBar = new SeekBar(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        gridLayout.addView(seekBar, 4 * w, w / 2);

        // create the textViews to gridLayout
        label = new TextView[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (col == 0) {
                    label[row][col] = new TextView(this);
                    String str = row == 0 ? "Tip" : "Total";
                    label[row][col].setText(str);
                    label[row][col].setTextColor(Color.BLACK);
                    label[row][col].setGravity(Gravity.CENTER);
                    gridLayout.addView(label[row][col], w, w);
                } else {
                    label[row][col] = new TextView(this);
                    label[row][col].setText("$0.00");
                    label[row][col].setTextColor(Color.BLACK);
                    label[row][col].setBackgroundColor(Color.rgb(255, 228, 181));
                    label[row][col].setGravity(Gravity.CENTER);
                    gridLayout.addView(label[row][col], 4 * w, w / 4 * 3);
                }
            }
        }

        setContentView(gridLayout);

        // textChangeHandler
        TextChangeHandler tch = new TextChangeHandler();
        input.addTextChangedListener(tch);

    }

    public void calculate() {
        int progress = seekBar.getProgress();
        try{
            percent.setText(progress + "%");
            double curAmount = txt;
            double tip = progress * curAmount / 100.0;
            double total = curAmount * (1 + progress / 100.0);
            label[0][1].setText("$" + String.format("%.2f", tip));
            label[1][1].setText("$" + String.format("%.2f", total));
        }catch (Exception e) {
            Log.i("exception", "exception" + e.toString());
        }
    }


    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        private String current = "";
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                if (!s.toString().equals(current)){
                    input.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");
                    double parsed = Double.parseDouble(cleanString);
                    Log.i("textchange parsed", String.valueOf(parsed));

                    String formatted = String.format("%.2f", parsed / 100);
                    Log.i("textchange formatted", formatted);

                    current = formatted;
                    input.setText(formatted);
                    txt = Double.parseDouble(formatted);

                    txt = (input.getText().toString().equals(""))
                            ? 0.00 : Double.parseDouble(input.getText().toString());
                    Log.i("textchange", String.valueOf(txt));
                    calculate();

                    input.setSelection(formatted.length());
                    input.addTextChangedListener(this);

                }
            }catch (Exception e){
                Log.i("exception", "on text changed");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}


