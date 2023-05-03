package com.example.projectskripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements SensorEventListener {
    float lastX, lastY, lastZ;
    SensorManager sensorManager;
    Sensor accelerometer;

    float deltaX = 0;
    float deltaY = 0;
    float deltaZ = 0;
    float vibrateThreshold = 0;
    Vibrator v;
    TextView currentX, currentY, currentZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
         if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
             accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
             sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
             vibrateThreshold = accelerometer.getMaximumRange() / 2;
         } else {

         }
         v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void initializeviews() {
        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        currentZ = (TextView) findViewById(R.id.currentZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        displayCleanValues();
        displayCurrentValues();

        deltaX = Math.abs(lastX - sensorEvent.values[0]);
        deltaY = Math.abs(lastY - sensorEvent.values[1]);
        deltaZ = Math.abs(lastZ - sensorEvent.values[2]);
    }

    public void displayCleanValues(){
        currentX.setText("0.0");
        currentY.setText("0.0");
        currentZ.setText("0.0");
    }
    public void displayCurrentValues(){
        currentX.setText(Float.toString(deltaX));
        currentY.setText(Float.toString(deltaY));
        currentZ.setText(Float.toString(deltaZ));
    }
}