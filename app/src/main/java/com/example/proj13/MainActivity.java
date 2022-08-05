package com.example.proj13;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int BLUETOOTH_REQ_CODE = 1;
    Button buttonBlue;
    Button home_mainButton;
    TextView connectionStatus;
    ImageView statusImage;
    ImageButton phone_info;
    ImageButton phone_save;
    ImageButton phone_Connect;
    ImageButton watch_save;
    ImageButton esense_save;
    ImageButton arduino_save;
    BluetoothAdapter bluetoothAdapter;

    protected static boolean clicked_phone = false;
    int isClicked=0;
    private Chronometer chronometer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBlue = (Button) findViewById(R.id.connect);
        connectionStatus = (TextView) findViewById(R.id.connectionStatus);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        phone_info = (ImageButton) findViewById(R.id.phone_info);
        phone_save = (ImageButton) findViewById(R.id.phone_save);
        phone_Connect = (ImageButton)findViewById(R.id.phone_Connect);
        watch_save = (ImageButton) findViewById(R.id.watch_save);
        esense_save = (ImageButton) findViewById(R.id.esense_save);
        arduino_save = (ImageButton) findViewById(R.id.arduino_save);
        home_mainButton = (Button)findViewById(R.id.home_mainbutton);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "This device doesn't support Bluetooth",
                    Toast.LENGTH_LONG).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            buttonBlue.setText("Connect");
            connectionStatus.setText("Disconnected");
            statusImage.setImageResource(R.drawable.disconnected);
            phone_Connect.setImageResource(R.drawable.disconnected);
            watch_save.setImageResource(R.drawable.ic_not_ok);
            esense_save.setImageResource(R.drawable.ic_not_ok);
            arduino_save.setImageResource(R.drawable.ic_not_ok);
            phone_save.setEnabled(false);
            home_mainButton.setEnabled(false);
        } else {
            buttonBlue.setText("Disconnect");
            connectionStatus.setText("Connected");
            statusImage.setImageResource(R.drawable.connected);
            phone_Connect.setImageResource(R.drawable.connected);
            phone_save.setImageResource(R.drawable.ic_circle);
            watch_save.setImageResource(R.drawable.ic_circle);
            esense_save.setImageResource(R.drawable.ic_circle);
            arduino_save.setImageResource(R.drawable.ic_circle);
            phone_save.setEnabled(true);
        }

        buttonBlue.setOnClickListener(this);
        phone_info.setOnClickListener(this);
        phone_save.setOnClickListener(this);
        home_mainButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
            buttonBlue.setText("Disconnect");
            connectionStatus.setText("Connected");
            statusImage.setImageResource(R.drawable.connected);
            phone_Connect.setImageResource(R.drawable.connected);
            phone_save.setImageResource(R.drawable.ic_circle);
            watch_save.setImageResource(R.drawable.ic_circle);
            esense_save.setImageResource(R.drawable.ic_circle);
            arduino_save.setImageResource(R.drawable.ic_circle);
            phone_save.setEnabled(true);
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Bluetooth operation is cancelled",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connect:
                if (!bluetoothAdapter.isEnabled()) {
                    Intent blueToothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(blueToothIntent, BLUETOOTH_REQ_CODE);
                } else {
                    bluetoothAdapter.disable();
                    buttonBlue.setText("Connect");
                    connectionStatus.setText("Disconnected");
                    statusImage.setImageResource(R.drawable.disconnected);
                    phone_Connect.setImageResource(R.drawable.disconnected);
                    watch_save.setImageResource(R.drawable.ic_not_ok);
                    esense_save.setImageResource(R.drawable.ic_not_ok);
                    arduino_save.setImageResource(R.drawable.ic_not_ok);
                    phone_save.setEnabled(false);
                    home_mainButton.setEnabled(false);
                }
                break;
            case R.id.phone_info:
                Intent intent = new Intent(this,Phone_Sensor.class);
                startActivity(intent);
                break;
            case R.id.phone_save:
                if (isClicked%2 == 0){
                    phone_save.setImageResource(R.drawable.ic_ok);
                    clicked_phone = true;
                    home_mainButton.setEnabled(true);
                }else {
                    phone_save.setImageResource(R.drawable.ic_circle);
                    clicked_phone = false;
                    home_mainButton.setEnabled(false);
                }
                isClicked=isClicked+1;
                break;
            case R.id.home_mainbutton:
                Intent intent1 = new Intent(this,Phone_Measure.class);
                startActivity(intent1);
                if (!running){
                    chronometer.start();
                    running = true;
                }
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}