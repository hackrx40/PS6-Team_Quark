package com.example.drive_fit_.fragmentclass;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drive_fit_.R;
import com.example.drive_fit_.activityclass.Rewards;
import com.example.drive_fit_.activityclass.StartDrive;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drive_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drive_dashboard extends Fragment implements SensorEventListener {
    
    TextView AccX, AccY, AccZ;
    LinearLayout startDrive;
    CardView Rewards;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public drive_dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment drive_dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static drive_dashboard newInstance(String param1, String param2) {
        drive_dashboard fragment = new drive_dashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drive_dashboard, container, false);


        Rewards = view.findViewById(R.id.rewards);
        Rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Rewards.class);
                startActivity(i);
            }
        });

        startDrive = view.findViewById(R.id.startDrive);
        startDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StartDrive.class);
                startActivity(i);
            }
        });

        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null)
        {
            Sensor acceleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if(acceleroSensor!=null)
            {
                sensorManager.registerListener(this,acceleroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }

            if(gyroSensor!=null)
            {
                sensorManager.registerListener(this,gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }

            if(stepSensor!=null)
            {
                sensorManager.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Sensor Not Detected",Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER)
        {
            AccX =  getView().findViewById(R.id.id_accelerationX);
            AccX.setText("X: " +event.values[0]);
//            Log.d("tejash",event.values[0]+" ");
//            Toast.makeText(getActivity(),AccX+"X_Axis",Toast.LENGTH_SHORT).show();

            AccY =  getView().findViewById(R.id.id_accelerationY);
            AccY.setText("Y: " +event.values[1]);
//            Log.d("tejash",event.values[1]+" ");

            AccZ = getView().findViewById(R.id.id_accelerationZ);
            AccZ.setText("Z: " +event.values[2]);
            Log.d("tejash_Acc","X axis: "+event.values[0]+", Y axis: "+ event.values[1]+ ", Z axis: "+ event.values[2]);
        }

        if(event.sensor.getType()== Sensor.TYPE_GYROSCOPE)
        {
            AccX =  getView().findViewById(R.id.id_gyroX);
            AccX.setText("X: " +event.values[0]);
//            Log.d("tejash",event.values[0]+" ");
//            Toast.makeText(getActivity(),AccX+"X_Axis",Toast.LENGTH_SHORT).show();

            AccY =  getView().findViewById(R.id.id_gyroY);
            AccY.setText("Y: " +event.values[1]);
//            Log.d("tejash",event.values[1]+" ");

            AccZ = getView().findViewById(R.id.id_gyroZ);
            AccZ.setText("Z: " +event.values[2]);
            Log.d("tejash_gyro","X axis: "+event.values[0]+", Y axis: "+ event.values[1]+ ", Z axis: "+ event.values[2]);
        }

        if(event.sensor.getType()== Sensor.TYPE_STEP_COUNTER)
        {
            AccX =  getView().findViewById(R.id.id_steps);
//            AccX.setText("X: " +event.values[0]);
////            Log.d("tejash",event.values[0]+" ");
////            Toast.makeText(getActivity(),AccX+"X_Axis",Toast.LENGTH_SHORT).show();
//
//            AccY =  getView().findViewById(R.id.id_gyroY);
//            AccY.setText("Y: " +event.values[1]);
////            Log.d("tejash",event.values[1]+" ");
//
//            AccZ = getView().findViewById(R.id.id_gyroZ);
//            AccZ.setText("Z: " +event.values[2]);
            Log.d("tejash_steps","X axis: "+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}