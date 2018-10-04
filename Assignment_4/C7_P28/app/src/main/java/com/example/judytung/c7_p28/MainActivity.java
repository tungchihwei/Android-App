package com.example.judytung.c7_p28;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

    public static int STATUS_BAR_HEIGHT = 24;
    public static int ACTION_BAR_HEIGHT = 56;
    private TextView txt_fling;
    RelativeLayout rl;
    private RelativeLayout.LayoutParams params;
    private GestureDetector ges_detec;
    int windowWidth;
    int windowHeight;
    int startX;
    int startY;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildGuiByCode();



        ges_detec = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                Log.i("check", "down");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                Log.i("check", "show press");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Log.i("check", "single tap");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                Log.i("check", "scroll");

                int Y = (int)(motionEvent1.getY()- motionEvent.getY());
                int X = (int)(motionEvent1.getX()- motionEvent.getX());
//                int Y = (int)(motionEvent1.getRawY());
//                int X = (int)(motionEvent1.getRawX());
                startX += X;
                startY += Y;
                if (startX < 0 ) {
                    startX = 0;
                }else if (startX > windowWidth - 200){
                    startX = windowWidth - 200;
                }
                if (startY < 0) {
                    startY = 0;
                }else if (startY > windowHeight - 100)  {
                    startY = windowHeight - 100;
                }
                params.leftMargin = startX;
                params.topMargin = startY;
//                params.leftMargin = X + windowWidth/2;
//                params.topMargin = Y + windowHeight/2;
                txt_fling.setLayoutParams(params);

                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                Log.i("check", "long press");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                Log.i("check", "fling");
                //Log.w("startx",String.valueOf(v1));
                if (Math.abs(v1) < 4500){
                    int Y = (int)(motionEvent1.getRawY()- motionEvent.getRawY());
                    int X = (int)(motionEvent1.getRawX()- motionEvent.getRawX());

                    startX += X;
                    startY += Y;
                    if (startX < 0 ) {
                        startX = 0;
                    }else if (startX > windowWidth - 200){
                        startX = windowWidth - 200;
                    }
                    if (startY < 0) {
                        startY = 0;
                    }else if (startY > windowHeight - 100)  {
                        startY = windowHeight - 100;
                    }

                    params.leftMargin = startX;
                    params.topMargin = startY;
//                    params.leftMargin = X + windowWidth/2;
//                    params.topMargin = Y + windowHeight/2;
                    txt_fling.setLayoutParams(params);
                }
                else{
                    Log.i("check", "random!");
//                    DisplayMetrics displayMetrics = new DisplayMetrics();
//                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                    int height = displayMetrics.heightPixels;
//                    int width = displayMetrics.widthPixels;
//                    Point size = new Point();
//                    getWindowManager().getDefaultDisplay().getSize(size);
//
//                    Resources res = getResources();
//                    DisplayMetrics metrics = res.getDisplayMetrics();
//                    float pixelDensity = metrics.density;
//
//                    int actionBarHeight = (int)(pixelDensity*ACTION_BAR_HEIGHT);
//                    TypedValue tv = new TypedValue();
//                    if(getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
//                        actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,metrics);
//                    }
//
//                    int statusBarHeight = (int) (pixelDensity*STATUS_BAR_HEIGHT);
//                    int resourcedId = res.getIdentifier("status_bar_height","dimen","android");
//                    if(resourcedId != 0) {
//                        statusBarHeight = res.getDimensionPixelSize(resourcedId);
//                    }
//
//                    windowWidth = size.x;
//                    windowHeight = size.y - statusBarHeight - actionBarHeight;

                    startX = new Random().nextInt(windowWidth - 200);
                    startY = new Random().nextInt(windowHeight - 100);
                    params.leftMargin = startX;
                    params.topMargin = startY;

                    Log.i("loc", "Moving to: "+params.leftMargin+ " "+params.topMargin);
                    Toast.makeText(getApplicationContext(),"out of speed",Toast.LENGTH_SHORT).show();

                    txt_fling.setLayoutParams(params);
                }
                return true;
            }
        });

    }
    public void buildGuiByCode(){

        rl = new RelativeLayout(this);

//        RelativeLayout.LayoutParams.WRAP_CONTENT
        params = new RelativeLayout.LayoutParams(200, 100);


        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float pixelDensity = metrics.density;

        int actionBarHeight = (int)(pixelDensity*ACTION_BAR_HEIGHT);
        TypedValue tv = new TypedValue();
        if(getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,metrics);
        }

        int statusBarHeight = (int) (pixelDensity*STATUS_BAR_HEIGHT);
        int resourcedId = res.getIdentifier("status_bar_height","dimen","android");
        if(resourcedId != 0) {
            statusBarHeight = res.getDimensionPixelSize(resourcedId);
        }

        windowWidth = size.x;
        windowHeight = size.y - statusBarHeight - actionBarHeight;

        txt_fling = new TextView(this);
        txt_fling.setTextSize(32);
        txt_fling.setBackgroundColor(Color.CYAN);
//        txt_fling.setLayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txt_fling.setGravity(Gravity.CENTER);
        txt_fling.setText("FlingMe");

        startX =  windowWidth/2 - 100;
        startY = windowHeight/2 - 50;
        params.leftMargin = startX;
        params.topMargin = startY;


        rl.addView(txt_fling, params);

        setContentView(rl);

        txt_fling.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ges_detec.onTouchEvent(motionEvent);

                return true;
            }
        });
    }


}
