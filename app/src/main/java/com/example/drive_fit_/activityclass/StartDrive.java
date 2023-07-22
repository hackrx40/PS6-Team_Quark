package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.drive_fit_.R;
import com.example.drive_fit_.utils.DriveScore;

public class StartDrive extends AppCompatActivity {

    DriveScore DS;
    SensorManager sensorManager;
    Sensor counterSensor;
    Sensor gyroSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_drive);

        DS = new DriveScore(this);
        sensorManager = DS.getSensorManager();
        counterSensor = DS.getCounterSensor();
        gyroSensor = DS.getGyroSensor();

    }

    @Override
    protected void onPause() {

        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            sensorManager.unregisterListener((SensorEventListener) this, counterSensor);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            sensorManager.unregisterListener((SensorEventListener) this, gyroSensor);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!=null)
        {
            Toast.makeText(this,"registered", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener((SensorEventListener) this,counterSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            sensorManager.registerListener((SensorEventListener) this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }



    }

}