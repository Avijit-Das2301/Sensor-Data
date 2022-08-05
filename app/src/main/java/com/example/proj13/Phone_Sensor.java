package com.example.proj13;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

public class Phone_Sensor extends MainActivity implements SensorEventListener {
    private static final String TAG = "Phone_Sensor";
    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumid;
    ImageButton accel_save;
    ImageButton gyro_save;
    ImageButton magno_save;
    ImageButton light_save;
    ImageButton pres_save;
    ImageButton temp_save;
    ImageButton humid_save;
    Button home_mainButton1;

    int is_accel = 0;
    int is_gyro = 0;
    int is_magno = 0;
    int is_light = 0;

    protected static boolean clicked_accel = false;
    protected static boolean clicked_gyro = false;
    protected static boolean clicked_magno = false;
    protected static boolean clicked_light = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_phone_sensor);

        accel_save = (ImageButton) findViewById(R.id.accel_save);
        gyro_save = (ImageButton) findViewById(R.id.gyro_save);
        magno_save = (ImageButton) findViewById(R.id.magno_save);
        light_save = (ImageButton) findViewById(R.id.light_save);
        pres_save = (ImageButton) findViewById(R.id.pres_save);
        temp_save = (ImageButton) findViewById(R.id.temp_save);
        humid_save = (ImageButton) findViewById(R.id.humid_save);
        home_mainButton1 = (Button)findViewById(R.id.home_mainbutton1);

        accel_save.setOnClickListener(this);
        gyro_save.setOnClickListener(this);
        magno_save.setOnClickListener(this);
        light_save.setOnClickListener(this);
        pres_save.setOnClickListener(this);
        temp_save.setOnClickListener(this);
        humid_save.setOnClickListener(this);
        home_mainButton1.setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Phone Sensor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            accel_save.setImageResource(R.drawable.ic_circle);
        } else {
            accel_save.setImageResource(R.drawable.ic_not_ok);
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            gyro_save.setImageResource(R.drawable.ic_circle);
        } else {
            gyro_save.setImageResource(R.drawable.ic_not_ok);
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null) {
            magno_save.setImageResource(R.drawable.ic_circle);
        } else {
            magno_save.setImageResource(R.drawable.ic_not_ok);
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mLight != null) {
            light_save.setImageResource(R.drawable.ic_circle);
        } else {
            light_save.setImageResource(R.drawable.ic_not_ok);
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (mPressure != null) {
            pres_save.setImageResource(R.drawable.ic_circle);
        } else {
            pres_save.setImageResource(R.drawable.ic_not_ok);
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mTemp != null) {
            temp_save.setImageResource(R.drawable.ic_circle);
        } else {
            temp_save.setImageResource(R.drawable.ic_not_ok);
        }

        mHumid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (mHumid != null) {
            humid_save.setImageResource(R.drawable.ic_circle);
        } else {
            humid_save.setImageResource(R.drawable.ic_not_ok);
        }

        if (clicked_phone)
            home_mainButton1.setEnabled(true);
        else {
            home_mainButton1.setEnabled(false);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accel_save:
                if (clicked_phone) {
                    if (is_accel % 2 == 0) {
                        clicked_accel = true;
                        accel_save.setImageResource(R.drawable.ic_ok);
                    } else {
                        clicked_accel = false;
                        accel_save.setImageResource(R.drawable.ic_circle);
                    }
                    is_accel = is_accel + 1;
                }
                break;
            case R.id.gyro_save:
                if (clicked_phone) {
                    if (is_gyro % 2 == 0) {
                        clicked_gyro = true;
                        gyro_save.setImageResource(R.drawable.ic_ok);
                    } else {
                        clicked_gyro = false;
                        gyro_save.setImageResource(R.drawable.ic_circle);
                    }
                    is_gyro = is_gyro + 1;
                }
                break;
            case R.id.magno_save:
                if (clicked_phone) {
                    if (is_magno % 2 == 0) {
                        clicked_magno = true;
                        magno_save.setImageResource(R.drawable.ic_ok);
                    } else {
                        clicked_magno = false;
                        magno_save.setImageResource(R.drawable.ic_circle);
                    }
                    is_magno = is_magno + 1;
                }
                break;
            case R.id.light_save:
                if (clicked_phone) {
                    if (is_light % 2 == 0) {
                        clicked_light = true;
                        light_save.setImageResource(R.drawable.ic_ok);
                    } else {
                        clicked_light = false;
                        light_save.setImageResource(R.drawable.ic_circle);
                    }
                    is_light = is_light + 1;
                }
                break;
            case R.id.home_mainbutton1:
                Intent intent = new Intent(this,Phone_Measure_Selected.class);
                startActivity(intent);
                break;
        }
    }
}