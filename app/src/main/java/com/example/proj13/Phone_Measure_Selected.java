package com.example.proj13;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Phone_Measure_Selected extends Phone_Sensor implements SensorEventListener {
    private static final String TAG = "Phone_Measure_Selected";
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight;
    TextView xValue, yValue, zValue,
            xGyroValue, yGyroValue, zGyroValue,
            xMagnoValue, yMagnoValue, zMagnoValue,
            light;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_phone_measure1);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView) findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView) findViewById(R.id.zMagnoValue);

        light = (TextView) findViewById(R.id.light);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer listener");
        } else {
            xValue.setText("Accelerometer not Supported");
            yValue.setText("Accelerometer not Supported");
            zValue.setText("Accelerometer not Supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            sensorManager.registerListener(this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope listener");
        } else {
            xGyroValue.setText("Gyroscope not Supported");
            yGyroValue.setText("Gyroscope not Supported");
            zGyroValue.setText("Gyroscope not Supported");
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null) {
            sensorManager.registerListener(this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Magnetometer listener");
        } else {

            xMagnoValue.setText("Magnetometer not Supported");
            yMagnoValue.setText("Magnetometer not Supported");
            zMagnoValue.setText("Magnetometer not Supported");
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null) {
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Light listener");
        } else {

            light.setText("Light not Supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        Location location = null;
        if ((sensor.getType() == Sensor.TYPE_ACCELEROMETER)) {
            Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
            xValue.setText("X Value :\n" + (sensorEvent.values[0]));
            yValue.setText("Y Value :\n" + (sensorEvent.values[1]));
            zValue.setText("Z Value :\n" + (sensorEvent.values[2]));
        } else if ((sensor.getType() == Sensor.TYPE_GYROSCOPE)) {
            xGyroValue.setText("X Gyro Value :\n" + sensorEvent.values[0]);
            yGyroValue.setText("Y Gyro Value :\n" + sensorEvent.values[1]);
            zGyroValue.setText("Z Gyro Value :\n" + sensorEvent.values[2]);
        } else if ((sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)) {
            xMagnoValue.setText("X Magno Value :\n" + sensorEvent.values[0]);
            yMagnoValue.setText("Y Magno Value :\n" + sensorEvent.values[1]);
            zMagnoValue.setText("Z Magno Value :\n" + sensorEvent.values[2]);
        } else if ((sensor.getType() == Sensor.TYPE_LIGHT) && (clicked_light)) {
            light.setText("Light Value: " + sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
// &&(clicked_accel)
//         &&(clicked_gyro)
//         &&(clicked_magno)