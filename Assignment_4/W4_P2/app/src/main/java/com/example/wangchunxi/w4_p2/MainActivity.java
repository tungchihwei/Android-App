package com.example.wangchunxi.w4_p2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private EditText Usd_txt;
    private TextView Euro_txt;
    private TextView Yen_txt;
    private TextView Pound_txt;
    private TextView Rupi_txt;
    private double usd;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usd_txt = findViewById(R.id.Usd_txt);
        Euro_txt = findViewById(R.id.Euro_txt);
        Yen_txt = findViewById(R.id.Yen_txt);
        Pound_txt = findViewById(R.id.Pound_txt);
        Rupi_txt = findViewById(R.id.Rupi_txt);

        TextChangeHandler tch = new TextChangeHandler();
        Usd_txt.addTextChangedListener(tch);

        String str = Usd_txt.getText().toString();
        usd = str.equals("") ? 0.0 : Double.parseDouble(str);
        detector = new GestureDetector(this,this);

    }

    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        Log.i("!!!", "onTouchEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        try{
            Log.i("!!! x", String.valueOf(distanceX));
            Log.i("!!! y", String.valueOf(distanceY));
            if (distanceY > 0 ) {
                usd += 0.05;
            }else if (distanceY < 0){
                usd -= 0.05;
            }
            Usd_txt.setText(String.format("%.2f", usd));
            Log.i("!!!","onScroll, now usd is " + usd);
        }catch (Exception e){
            Log.i("!!!", "onScroll exception");
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try{
            Log.i("!!! vx", String.valueOf(velocityX));
            Log.i("!!! vy", String.valueOf(velocityY));
            Log.i("!!!","beforeOnFling, now usd is " + usd);
            if (velocityY > 0) {
                usd -= 1;
            }else if (velocityY < 0){
                usd += 1;
            }
            Usd_txt.setText(String.format("%.2f", usd));
            Log.i("!!!","AfterOnFling, now usd is " + usd);
        }catch (Exception e){
            Log.i("!!!", "onFling exception");
        }
        return true;
    }

    private class TextChangeHandler implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            usd = s.toString().equals("") ? 0.0 : Double.parseDouble(s.toString());
            calculate();
        }
    }

    public void calculate() {
        try{
            double euro = 0.86 * usd;
            Euro_txt.setText(String.format("%.2f",euro));
            double yen =  113.57 * usd;
            Yen_txt.setText(String.format("%.2f", yen));
            double pound =  0.77 * usd;
            Pound_txt.setText(String.format("%.2f", pound));
            double rupi =  72.52 * usd;
            Rupi_txt.setText(String.format("%.2f", rupi));

        }catch (Exception e) {
            Log.i("!!!Exception", "text changed");
        }
    }

}
