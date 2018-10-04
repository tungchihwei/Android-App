package com.example.tz1993.w4_p3;

import android.content.Context;
import android.webkit.WebView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private String TAG = "ACCELEROMETER";

    private SeekBar sbAccel;

    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;
    private float lastX, lastY, lastZ;

    private WebView webURL;

    private static int SIGNIFICANT_SHAKE = 11;
    private static int SIGNIFICANT_ACCE = 80000;
    private boolean flag = false;

    private TextView sig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acceleration = 0.00f;                                         //Initializing Acceleration data.
        currentAcceleration = SensorManager.GRAVITY_EARTH;            //We live on Earth.
        lastAcceleration = SensorManager.GRAVITY_EARTH;
        setContentView(R.layout.activity_main);

        sbAccel = (SeekBar) findViewById(R.id.accelSB);
        webURL = (WebView) findViewById(R.id.webURL);
        sig = (TextView) findViewById(R.id.sig);

        sbAccel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                SIGNIFICANT_SHAKE = progress;

                sig.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        enableAccelerometerListening();
    }

    @Override
    protected void onStop() {
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
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        // stop listening for accelerometer events
        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];   //always do this first
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2]-9.8f;

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;   //This is a simplified calculation, to be real we would need time and a square root.

            // calculate the change in acceleration        //Also simplified, but good enough to determine random shaking.
            acceleration = currentAcceleration *  (currentAcceleration - lastAcceleration);
            Log.e("acceleration", String.valueOf(acceleration));

            String url;

            float deltaX = Math.abs(x - lastX);
            float deltaY = Math.abs(y - lastY);
            float deltaZ = Math.abs(z - lastZ);

            float maxMove = Math.max(Math.max(deltaX,deltaY),deltaZ);
            Log.e("maxMove!!!", String.valueOf(maxMove));

            if (acceleration > SIGNIFICANT_ACCE) {
                url = "https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg";
                webURL.loadUrl(url);
                flag = true;
                Log.e("flag1", Boolean.toString(flag));
            }
            else if ( maxMove > SIGNIFICANT_SHAKE  && acceleration < 1000 && !flag) {
                webURL.loadUrl("about:blank");
                Log.e("flag2", Boolean.toString(flag));
                Log.e(TAG, "SIG SHAKE = " + Integer.toString(SIGNIFICANT_SHAKE));
                Toast.makeText(getApplicationContext(), "Nice Move!",  Toast.LENGTH_LONG).show();
                // find largest change in dimension
                if (deltaX > deltaY && deltaX > deltaZ) {
                    // x is greatest; open Google
                    Log.e("changeX", Boolean.toString(deltaX > deltaY && deltaX > deltaZ));
                    url = "http://google.com";
                    webURL.setWebViewClient(new WebViewClient());
                    webURL.loadUrl(url);
                }

                else if (deltaY > deltaX && deltaY > deltaZ) {
                    // y is greatest; open Yahoo
                    Log.e("changeY", Boolean.toString(deltaY > deltaX && deltaY > deltaZ));
                    url = "http://yahoo.com";
                    webURL.setWebViewClient(new WebViewClient());
                    webURL.loadUrl(url);
                }

                else if (deltaZ > deltaX && deltaZ > deltaY){
                    // z is greatest; open Bing
                    Log.e("changeZ", Boolean.toString(deltaZ > deltaX && deltaZ > deltaY));
                    url = "http://bing.com";
                    webURL.setWebViewClient(new WebViewClient());
                    webURL.loadUrl(url);
                }

            }
            else if (flag) {

                new Timer().schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                flag = false;
                            }
                        },
                        5000
                );
            }

            lastX = x;
            lastY = y;
            lastZ = z;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

}
