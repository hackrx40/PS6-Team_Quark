package com.example.drive_fit_.utils;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DriveScore implements SensorEventListener {

    private SensorManager sensorManager;

    public float F1=0.0f,F2=0.0f,F3=0.0f,F4=0.0f,F5=0.0f;

    public float Score;
    private int dataCountAcc = 0;
    private float sumData_x = 0;
    private float sumData_y = 0;
    private float sumData_z = 0;
    private Float sumData_x_dcc = 0.0f;
    private Float sumData_y_dcc = 0.0f;
    private Float sumData_z_dcc = 0.0f;
    private int dataCountDcc = 0;
    private float lastX, lastY, lastZ;
    private float lastGyroX, lastGyroY, lastGyroZ;
    private float sumGyroZ = 0.0f;
    private int dataCountGyroZ = 0;
    private float cornerSum = 0;
    private int datacnt = 0;
    private static final float GRAVITY = 9.81f;
    private Sensor counterSensor;
    private Sensor gyroSensor;
    private boolean isCounterSensorPresent;

    public Sensor getCounterSensor(){
        return counterSensor;
    }

    public Sensor getGyroSensor(){
        return gyroSensor;
    }

    public SensorManager getSensorManager(){
        return sensorManager;
    }

    public DriveScore(Context context) {

        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!=null)
        {
            Toast.makeText(context,"Counter Sensor present", Toast.LENGTH_SHORT).show();
            counterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            isCounterSensorPresent = true;
        }
        else {
            Toast.makeText(context,"Counter Sensor Not present", Toast.LENGTH_SHORT).show();
            isCounterSensorPresent = false;
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }

    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float x,y,z;
        z=0.0f;
        y=0.0f;
        x=0.0f;

        //AcceleratometerModel model = new AcceleratometerModel();

        if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {
            //accList.add(new AcceleratometerModel(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]));
//            model.setAccX(sensorEvent.values[0]);
//            model.setAccY(sensorEvent.values[1]);
//            model.setAccZ(sensorEvent.values[2]);

            final DecimalFormat decfor = new DecimalFormat("0.0000");

            x = Float.parseFloat(decfor.format(sensorEvent.values[0]));
            y = Float.parseFloat(decfor.format(sensorEvent.values[1]));
            z = Float.parseFloat(decfor.format(sensorEvent.values[2]));


            Log.d("soumen",x+" y:"+y+" z:"+z+" "+dataCountAcc);

            //if iteration of acc is positive -> ACCELERATION
            if(y>=0.2)
            {
                dataCountAcc++;
                sumData_x += x;
                sumData_y += y;
                sumData_z += z;
                if(y<4) {
                    F1 = 4 - (y/4);
                }
                else{
                    F1 = 0;
                }

                SystemClock.sleep(3000);
            }
            else if(y<-0.2)
            {
                dataCountDcc++;
                sumData_x_dcc += x;
                sumData_y_dcc += y;
                sumData_z_dcc += z;
                if(y>-5){
                    F2 = Math.abs(y/5);
                }
                else{
                    F2 = 0;
                }

                SystemClock.sleep(3000);
            }

            Log.i("Score","F1: "+ F1 + "F2: " + F2);

        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            Log.d("soumen gyro",sensorEvent.values[0]+" y:"+sensorEvent.values[1]+" z:"+sensorEvent.values[2]+" "+datacnt);

//            model.setGyroX(sensorEvent.values[0]);
//            model.setGyroY(sensorEvent.values[1]);
//            model.setGyroZ(sensorEvent.values[2]);
            // Calculate centripetal acceleration
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;
            float centripetalAcceleration = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

            // Get gyroscopic rotation rates
            float[] gyroValues = sensorEvent.values;
            float gyroX = gyroValues[0];
            float gyroY = gyroValues[1];
            float gyroZ = gyroValues[2];

            if(gyroZ >= 1)
            {
                sumGyroZ += gyroZ;
                dataCountGyroZ++;
            }

            // Integrate gyroscopic rotation rates to estimate change in angle
            float deltaAngleX = (gyroX + lastGyroX) * 0.5f; // Convert to radians
            float deltaAngleY = (gyroY + lastGyroY) * 0.5f; // Convert to radians
            float deltaAngleZ = (gyroZ + lastGyroZ) * 0.5f; // Convert to radians

            // Estimate the radius of curvature using centripetal acceleration and angular velocity
            float radiusOfCurvature = (centripetalAcceleration > 0.0f) ? (float) Math.pow(GRAVITY / centripetalAcceleration,2) : 0.0f;

            float corneringSpeed = (float) Math.sqrt(centripetalAcceleration * radiusOfCurvature);

            lastGyroX = gyroX;
            lastGyroY = gyroY;
            lastGyroZ = gyroZ;

            cornerSum += corneringSpeed;
            datacnt++;


            lastX = x;
            lastY = y;
            lastZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
