package com.example.wangchunxi.w3_p3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private SeekBar C_sb;
    private SeekBar F_sb;
    private TextView C_num;
    private TextView F_num;
    private TextView msg;
    private String f;
    private String c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        C_sb = (SeekBar) findViewById(R.id.C_sb);
        F_sb = (SeekBar) findViewById(R.id.F_sb);
        C_num = (TextView) findViewById(R.id.C_num);
        F_num = (TextView) findViewById(R.id.F_num);
        msg = (TextView) findViewById(R.id.msg);

        C_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try{
                    double cur_f = 32.0 + 9.0 / 5.0 * progress;
                    Log.i("progress cur", String.valueOf(cur_f));
                    String temp = String.format("%.2f", cur_f).replaceAll(",", ".");
                    c = String.format("%.2f", (double)progress).replaceAll(",", ".");
                    Log.i("progress temp", temp);
                    if (F_num.getText().toString().equals(f)){
                        Log.i("progress equal", "equal");
                        C_num.setText(c);
                        F_num.setText(temp);
                    }else {
                        C_num.setText(c);
                        f = temp;
                        F_num.setText(temp);
                        F_sb.setProgress((int)cur_f);
                    }
                    setMsg();
                } catch(Exception e) {
                    Log.i("progress", "exception C_sb" + e.toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        F_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try{
                    double cur_c = 5.0 / 9.0 * (progress - 32.0);
                    Log.i("progress cur f", String.valueOf(cur_c));
                    String temp = String.format("%.2f", cur_c).replaceAll(",", ".");
                    Log.i("progress temp f", temp);
                    f = String.format("%.2f", (double)progress).replaceAll(",", ".");
                    if (progress <= 32) {
                        F_sb.setProgress(32);
                        C_sb.setProgress(0);
                        F_num.setText("32.00");
                        C_num.setText("0.00");
                    }else {
                        if (C_num.getText().toString().equals(c)){
                            Log.i("progress equal f", "equal");
                            F_num.setText(f);
                            C_num.setText(temp);
                        }else {
                            F_num.setText(f);
                            c = temp;
                            C_num.setText(temp);
                            C_sb.setProgress((int)cur_c);
                        }
                    }
                    setMsg();
                } catch(Exception e) {
                    Log.i("progress", "exception F_sb" + e.toString());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void setMsg() {
        if (Double.parseDouble(C_num.getText().toString()) < 12.0) {
            msg.setText(R.string.cold);
        }else {
            msg.setText(R.string.not_cold);
        }
    }


}
