package com.example.drive_fit_.activityclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.drive_fit_.modelClass.AcceleratometerModel;
import com.example.drive_fit_.R;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class StepTracking extends AppCompatActivity implements SensorEventListener {


    private long startTime;
    private int dataCountAcc;
    private int dataCountDcc;
    private float sumData_x;
    private float sumData_y;
    private float sumData_z;

    private float cornerSum = 0;
    private int datacnt = 0;


    private Float sumData_x_dcc;
    private Float sumData_y_dcc;
    private Float sumData_z_dcc;

    private Float velocity = 0.0f;

    private float lastX, lastY, lastZ;
    private float lastGyroX, lastGyroY, lastGyroZ;
    private static final int MIN_TIME_INTERVAL = 1000; // Minimum time interval in milliseconds
    private static final float GRAVITY = 9.81f; // Acceleration due to gravity in m/s^2

    private float sumGyroZ = 0.0f;
    private int dataCountGyroZ = 0;


    ArrayList<AcceleratometerModel> accList = new ArrayList<AcceleratometerModel>();

    private float finalCorner = 0.0f;
    private float finalAcc = 0.0f;
    private float finalVec = 0.0f;
    private float finalDcc = 0.0f;


    private float totalCorner = 0.0f;
    private float totalAcc = 0.0f;
    private float totalDcc = 0.0f;
    private float totalVec = 0.0f;
    private float accFactor = 0.0f;
    private float dccFactor = 0.0f;
    private float vecFactor = 0.0f;
    private float corFactor = 0.0f;







    private SensorManager sensorManager;
    private Sensor counterSensor;
    private Sensor gyroSensor;

    private boolean isCounterSensorPresent;

    int stepsCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracking);


        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!=null)
        {
            Toast.makeText(this,"Counter Sensor present", Toast.LENGTH_SHORT).show();
            counterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            isCounterSensorPresent = true;
        }
        else {
            Toast.makeText(this,"Counter Sensor Not present", Toast.LENGTH_SHORT).show();
            isCounterSensorPresent = false;
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            sensorManager.unregisterListener(this, counterSensor);
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            sensorManager.unregisterListener(this, gyroSensor);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)!=null)
        {
            Toast.makeText(this,"registered", Toast.LENGTH_SHORT).show();
            sensorManager.registerListener(this,counterSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null)
        {
            sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

        startTime = System.currentTimeMillis();
        dataCountAcc = 0;
        dataCountDcc = 0;
        sumData_x = 0.0f;
        sumData_y = 0.0f;
        sumData_z = 0.0f;

        sumData_x_dcc = 0.0f;
        sumData_y_dcc = 0.0f;
        sumData_z_dcc = 0.0f;

        startTrackingAverage();

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

            stepsCount = (int) sensorEvent.values[0];

            final DecimalFormat decfor = new DecimalFormat("0.0000");

            x = Float.parseFloat(decfor.format(sensorEvent.values[0]));
            y = Float.parseFloat(decfor.format(sensorEvent.values[1]));
            z = Float.parseFloat(decfor.format(sensorEvent.values[2]));


            Log.d("soumen",x+" y:"+y+" z:"+z+" "+dataCountAcc);

            //if iteration of acc is positive -> ACCELERATION
            if(y>=2)
            {
                dataCountAcc++;
                sumData_x += x;
                sumData_y += y;
                sumData_z += z;
            }
            else if(y<-1)
            {
                dataCountDcc++;
                sumData_x_dcc += x;
                sumData_y_dcc += y;
                sumData_z_dcc += z;
            }

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

        Log.d("Export Data cnt",accList.size()+"");
        if(accList.size()==100)
        {
            if(checkPermissionAndExportData())
            {
                saveData(accList);
                //saveAccelerationDataToCSV(accList);
            }

        }

        //accList.add(model);
    }

    private static final int PERMISSION_REQUEST_CODE = 100;

    // Check if the permission is granted, and request it if not
    private Boolean checkPermissionAndExportData() {
        // Check if the permission has already been granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

            return true;
            // Permission is granted, proceed with exporting data

        } else {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Check if the requested permission has been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, proceed with exporting data

            } else {
                // Permission is denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }

    private void saveAccelerationDataToCSV(ArrayList<AcceleratometerModel> accelerationDataList) {
        String csvFileName = "acceleration_data.csv";
        String[] header = {"TimeStamp, Acceleration X", "Acceleration Y", "Acceleration Z", "Gyro X", "Gyro Y", "Gyro Z"};

        File file = new File(Environment.getExternalStorageDirectory(), csvFileName);

        if(!file.exists()){
            file.mkdir();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write header
            csvWriter.writeNext(header);

            for(int i=0;i<accelerationDataList.size();i++)
            {
                AcceleratometerModel accModel = accelerationDataList.get(i);
                String[] rowData = {
                        String.valueOf(System.currentTimeMillis()),
                        String.valueOf(accModel.getAccX()), // Timestamp
                        String.valueOf(accModel.getAccY()), // Acceleration X
                        String.valueOf(accModel.getAccZ()),// Acceleration Y
                        String.valueOf(accModel.getGyroX()), // Timestamp
                        String.valueOf(accModel.getGyroY()), // Acceleration X
                        String.valueOf(accModel.getGyroZ()),// Acceleration Y


                };
                csvWriter.writeNext(rowData);
            }


            // Close writers
            csvWriter.close();
            fileWriter.close();

            Toast.makeText(this,"file",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }




        StringBuilder csvData = new StringBuilder();
        csvData.append("Acceleration X,Acceleration Y,Acceleration Z\n"); // Header

        for(int i=0;i<accelerationDataList.size();i++)
        {
            AcceleratometerModel accModel = accelerationDataList.get(i);
            String[] rowData = {
                    String.valueOf(accModel.getAccX()), // Timestamp
                    String.valueOf(accModel.getAccY()), // Acceleration X
                    String.valueOf(accModel.getAccZ()), // Acceleration Y

            };
            csvData.append(rowData[0])
                    .append(",")
                    .append(rowData[1])
                    .append(",")
                    .append(rowData[2])
                    .append("\n");
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("accelerometer_data.csv", Context.MODE_PRIVATE);
            fos.write(csvData.toString().getBytes());
            Log.d("Export", "Accelerometer data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    private void saveData(ArrayList<AcceleratometerModel> accelerationDataList) {
        String csvFileName = "acceleration_data.csv";
        String[] header = {"TimeStamp", "Longitudinal Acc", "Longitudinal Braking", "Velocity", "Cornering Speed", "AccFactor", "DccFactor", "VecFactor", "CornerFactor", "DriverScore"};

        File file = new File(Environment.getExternalStorageDirectory(), csvFileName);

        if(!file.exists()){
            file.mkdir();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write header
            csvWriter.writeNext(header);

            for(int i=0;i<accelerationDataList.size();i++)
            {
                AcceleratometerModel accModel = accelerationDataList.get(i);
                String[] rowData = {
                        String.valueOf(accModel.getTimeStamp()),
                        String.valueOf(accModel.getTotalAcc()),
                        String.valueOf(accModel.getTotalDcc()),
                        String.valueOf(accModel.getTotalVel()),
                        String.valueOf(accModel.getTotalCorner()),
                        String.valueOf(accModel.getAccFactor()),
                        String.valueOf(accModel.getDccFactor()),
                        String.valueOf(accModel.getVelFactor()),
                        String.valueOf(accModel.getCorFactor()),
                        String.valueOf(accModel.getDriverScore()),


                };
                csvWriter.writeNext(rowData);
            }


            // Close writers
            csvWriter.close();
            fileWriter.close();

            Toast.makeText(this,"file written",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }




        StringBuilder csvData = new StringBuilder();
        csvData.append("Acceleration X,Acceleration Y,Acceleration Z\n"); // Header

        for(int i=0;i<accelerationDataList.size();i++)
        {
            AcceleratometerModel accModel = accelerationDataList.get(i);
            String[] rowData = {
                    String.valueOf(accModel.getAccX()), // Timestamp
                    String.valueOf(accModel.getAccY()), // Acceleration X
                    String.valueOf(accModel.getAccZ()), // Acceleration Y

            };
            csvData.append(rowData[0])
                    .append(",")
                    .append(rowData[1])
                    .append(",")
                    .append(rowData[2])
                    .append("\n");
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("accelerometer_data.csv", Context.MODE_PRIVATE);
            fos.write(csvData.toString().getBytes());
            Log.d("Export", "Accelerometer data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void startTrackingAverage() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                float driverScore = 0.0f;

                float finalvelocity = 0.0f;
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= 1000) {
                    // Calculate average for the last second
                    if (dataCountAcc >= 0) {
                        float average_x = 0.0f;
                        float average_y = 0.0f;
                        float average_z = 0.0f;

                        if(dataCountAcc>0)
                        {
                            average_x = sumData_x/ dataCountAcc;
                            average_y = sumData_y/ dataCountAcc;
                            average_z = sumData_z/ dataCountAcc;
                        }

                        // Reset variables for the next second
                        startTime = currentTime;
                        dataCountAcc = 0;
                        sumData_y = 0.0f;
                        sumData_x = 0.0f;
                        sumData_z = 0.0f;
                        Log.d("Soumen Paul Acc", average_y+"");



                        float deltaTime = (currentTime - startTime) / 1000.0f; // Convert to seconds


                        // Integrate the acceleration to update the velocity
                        //velocity = acceleration * deltaTime;
                        finalvelocity = (velocity + (average_y))/2;
                        velocity = finalvelocity;

                        accFactor = (float) ((0.2)*(Math.abs(finalAcc-average_y)/(10)));

                        driverScore += (0.2)*(Math.abs(finalAcc-average_y)/(10));
                        finalAcc = average_y;

                        totalAcc = average_y;
                        totalVec = finalvelocity;

                    }

                    if(dataCountDcc >= 0)
                    {
                        float average_x = 0.0f;
                        float average_y = 0.0f;
                        float average_z = 0.0f;

                        if(dataCountDcc>0)
                        {
                            average_x = sumData_x_dcc/ dataCountDcc;
                            average_y = sumData_y_dcc/ dataCountDcc;
                            average_z = sumData_z_dcc/ dataCountDcc;
                        }


                        // Reset variables for the next second
                        dataCountDcc = 0;
                        sumData_y_dcc = 0.0f;
                        sumData_x_dcc = 0.0f;
                        sumData_z_dcc = 0.0f;
                        Log.d("Soumen Paul Dcc", average_y+"");

                        dccFactor = (float) ((0.3)*(Math.abs(finalDcc-average_y)/(10)));
                        totalDcc = average_y;

                        driverScore += (0.3)*(Math.abs(finalDcc-average_y)/(10));
                        finalDcc = average_y;
                    }

                    float avgCorner = 0.0f;
                    if(datacnt>0)
                    {
                        avgCorner = cornerSum/datacnt;
                        cornerSum = 0.0f;
                        datacnt = 0;
                    }

                    float avgGyroZ = 0.0f;
                    if(dataCountGyroZ>0)
                    {
                        avgGyroZ = sumGyroZ/dataCountGyroZ;
                    }


                    sumGyroZ = 0.0f;
                    dataCountGyroZ = 0;

                    float radius = 0.0f;

                    if(avgGyroZ > 1.0f)
                    {
                        radius = finalvelocity / avgGyroZ;
                    }

                    float cornerSpeed = (float) Math.sqrt(9.8f * radius);
                    totalCorner = cornerSpeed;

                    Log.d("Soumen Paul vel", finalvelocity+"");
                    Log.d("Soumen Paul corner", cornerSpeed+"");

                    vecFactor = (float) ((0.2)*(Math.abs(finalVec-finalvelocity)/(100)));
                    corFactor = (float) (0.3)*(Math.abs(finalCorner-cornerSpeed)/(30));

                    driverScore += (0.2)*(Math.abs(finalVec-finalvelocity)/(100));
                    driverScore += (0.3)*(Math.abs(finalCorner-cornerSpeed)/(30));

                    finalVec = finalvelocity;
                    finalCorner = cornerSpeed;

                    accList.add(new AcceleratometerModel(System.currentTimeMillis(), driverScore, totalAcc, totalDcc, accFactor, dccFactor, vecFactor, corFactor, totalVec, totalCorner));

                    Log.d("Soumen Paul Driver",driverScore+"\n");
                    Log.d("Soumen Paul List", accList.size()+"");

                }
            }
        }, 0, 100); // Check every 100 milliseconds (adjust as needed)
    }
}