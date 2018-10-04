package com.example.wangchunxi.w4_p4;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class South extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private String TAG = "!!!";
    private GestureDetector detector;
    private ImageView img;

    private float lastX, lastY, lastZ;  //old coordinate positions from accelerometer, needed to calculate delta.
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;

    // value used to determine whether user shook the device "significantly"
    private static int SIGNIFICANT_SHAKE = 30000;   //tweak this as necessary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_south);

        acceleration = 0.00f;                                         //Initializing Acceleration data.
        currentAcceleration = SensorManager.GRAVITY_EARTH;            //We live on Earth.
        lastAcceleration = SensorManager.GRAVITY_EARTH;               //Ctrl-Click to see where else we could use our phone.
        Log.i("!!!acceleration", String.valueOf(acceleration));
        Log.i("!!!currentAcceleration", String.valueOf(currentAcceleration));
        Log.i("!!!lastAcceleration", String.valueOf(currentAcceleration));

        img = findViewById(R.id.img_south);

        detector = new GestureDetector(this,this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Triggered.");
        enableAccelerometerListening();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop Triggered.");
        disableAccelerometerListening();
        super.onStop();
    }

    // enable listening for accelerometer events
    private void enableAccelerometerListening() {
        // The Activity has a SensorManager Reference.
        // This is how we get the reference to the device's SensorManager.
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);    //The last parm specifies the type of Sensor we want to monitor


        //Now that we have a Sensor Handle, let's start "listening" for movement (accelerometer).
        //3 parms, The Listener, Sensor Type (accelerometer), and Sampling Frequency.
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);   //don't set this too high, otw you will kill user's battery.
    }



    // disable listening for accelerometer events
    private void disableAccelerometerListening() {

//Disabling Sensor Event Listener is two step process.
        //1. Retrieve SensorManager Reference from the activity.
        //2. call unregisterListener to stop listening for sensor events
        //THis will prevent interruptions of other Apps and save battery.

        // get the SensorManager
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        // stop listening for accelerometer events
        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // get x, y, and z values for the SensorEvent
            //each time the event fires, we have access to three dimensions.
            //compares these values to previous values to determine how "fast"
            // the device was shaken.
            //Ref: http://developer.android.com/reference/android/hardware/SensorEvent.html

            float x = event.values[0];   //always do this first
            float y = event.values[1];
            float z = event.values[2];

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;   //This is a simplified calculation, to be real we would need time and a square root.

            // calculate the change in acceleration        //Also simplified, but good enough to determine random shaking.
            acceleration = currentAcceleration *  (currentAcceleration - lastAcceleration);

            Log.i("!!!acceleration", "Change"+String.valueOf(acceleration));
            Log.i("!!!currentAcceleration", "Change"+String.valueOf(currentAcceleration));
            Log.i("!!!lastAcceleration", "Change"+String.valueOf(currentAcceleration));

            // if the acceleration is above a certain threshold
            if (acceleration > SIGNIFICANT_SHAKE) {
                Log.e(TAG, "delta x = " + (x-lastX));
                Log.e(TAG, "delta y = " + (y-lastY));
                Log.e(TAG, "delta z = " + (z-lastZ));
                try{
                    final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    img.startAnimation(animShake);
                }catch (Exception e){
                    Log.i("!!!", String.valueOf(e));
                }
            }

            lastX = x;
            lastY = y;
            lastZ = z;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    public boolean onTouchEvent(MotionEvent e){
        detector.onTouchEvent(e);
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

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float x = velocityX;
        float y = velocityY;
        Log.i("!!! vx", String.valueOf(x));
        Log.i("!!! vy", String.valueOf(y));

        Intent intent;

        if (x >= 0 && y >= 0) {
            if (x - y > 0) {
                intent = new Intent(this, East.class);
                startActivity(intent);
            }else if (x - y < 0){
                intent = new Intent(this, South.class);
                startActivity(intent);
            }
        }else if (x > 0 && y < 0) {
            if (x > (-y)) {
                intent = new Intent(this, East.class);
                startActivity(intent);
            }else {
                intent = new Intent(this, North.class);
                startActivity(intent);
            }
        }else if (x < 0 && y > 0) {
            if (y > (-x)) {
                intent = new Intent(this, South.class);
                startActivity(intent);
            }else {
                intent = new Intent(this, West.class);
                startActivity(intent);
            }
        }else {
            if (-x > -y) {
                intent = new Intent(this, West.class);
                startActivity(intent);
            }else {
                intent = new Intent(this, North.class);
                startActivity(intent);
            }
        }
        return true;
    }
}
