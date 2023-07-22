package com.example.drive_fit_.fragmentclass;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link drive_dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class drive_dashboard extends Fragment {
    
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

        startDrive = view.findViewById(R.id.startDrive);
        startDrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StartDrive.class);
                startActivity(i);
            }
        });


        BarChart accbar = view.findViewById(R.id.accbar);
        List<BarEntry> entriesAcc = new ArrayList<>();
        entriesAcc.add(new BarEntry(0f, 500f));
        entriesAcc.add(new BarEntry(1f, 750f));
        entriesAcc.add(new BarEntry(2f, 1000f));
        BarDataSet dataSettime = new BarDataSet(entriesAcc, "Bar Data Set");
        dataSettime.setColors(new int[]{Color.parseColor("#CDDC39"),Color.parseColor("#FFC107"), Color.parseColor("#FF5722")});
        //dataSet.setBarRadius(15f);

        BarData barDatatime = new BarData(dataSettime);
        barDatatime.setBarWidth(0.5f);

        accbar.setData(barDatatime);
        accbar.setDrawGridBackground(false);
        accbar.getDescription().setEnabled(false);
        accbar.getLegend().setEnabled(false);
        accbar.setTouchEnabled(false);

        XAxis xAxistime = accbar.getXAxis();
        xAxistime.setGranularity(1f);
        xAxistime.setDrawAxisLine(false);
        xAxistime.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxistime.setDrawGridLines(false);
        xAxistime.setAxisMaximum(3.5f); // Adjust maximum value on x-axis
        xAxistime.setAxisMinimum(-0.5f);

        YAxis leftAxistime = accbar.getAxisLeft();
        leftAxistime.setDrawAxisLine(false);
        leftAxistime.setDrawGridLines(false);
        leftAxistime.setAxisMinimum(0f);
        leftAxistime.setAxisMaximum(1000f); // Adjust maximum value on y-axis

        YAxis rightAxistime = accbar.getAxisRight();
        rightAxistime.setDrawAxisLine(false);
        rightAxistime.setDrawGridLines(false);
        rightAxistime.setDrawLabels(false);

        accbar.invalidate();



//        BarChart gyrobar = view.findViewById(R.id.gyrobar);
//        List<BarEntry> entriesGyro = new ArrayList<>();
//        entriesGyro.add(new BarEntry(0f, 500f));
//        entriesGyro.add(new BarEntry(1f, 750f));
//        entriesGyro.add(new BarEntry(2f, 1000f));
//        BarDataSet dataSettimeGyro = new BarDataSet(entriesGyro, "Bar Data Set");
//        dataSettimeGyro.setColors(new int[]{Color.parseColor("#CDDC39"),Color.parseColor("#FFC107"), Color.parseColor("#FF5722")});
//        //dataSet.setBarRadius(15f);
//
//        BarData barDatatimeGyro = new BarData(dataSettimeGyro);
//        barDatatimeGyro.setBarWidth(0.5f);
//
//        gyrobar.setData(barDatatimeGyro);
//        gyrobar.setDrawGridBackground(false);
//        gyrobar.getDescription().setEnabled(false);
//        gyrobar.getLegend().setEnabled(false);
//        gyrobar.setTouchEnabled(false);
//
//        XAxis xAxistimeGyro = gyrobar.getXAxis();
//        xAxistimeGyro.setGranularity(1f);
//        xAxistimeGyro.setDrawAxisLine(false);
//        xAxistimeGyro.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxistimeGyro.setDrawGridLines(false);
//        xAxistimeGyro.setAxisMaximum(3.5f); // Adjust maximum value on x-axis
//        xAxistimeGyro.setAxisMinimum(-0.5f);
//
//        YAxis leftAxistimegyro = gyrobar.getAxisLeft();
//        leftAxistimegyro.setDrawAxisLine(false);
//        leftAxistimegyro.setDrawGridLines(false);
//        leftAxistimegyro.setAxisMinimum(0f);
//        leftAxistimegyro.setAxisMaximum(1000f); // Adjust maximum value on y-axis
//
//        YAxis rightAxistimegyro = gyrobar.getAxisRight();
//        rightAxistimegyro.setDrawAxisLine(false);
//        rightAxistimegyro.setDrawGridLines(false);
//        rightAxistimegyro.setDrawLabels(false);
//
//        accbar.invalidate();



        BarChart stepbar = view.findViewById(R.id.stepbar);
        List<BarEntry> entriesstep = new ArrayList<>();
        entriesstep.add(new BarEntry(0f, 500f));
        entriesstep.add(new BarEntry(1f, 750f));
        entriesstep.add(new BarEntry(2f, 1000f));
        BarDataSet dataSettimebar = new BarDataSet(entriesstep, "Bar Data Set");
        dataSettimebar.setColors(new int[]{Color.parseColor("#CDDC39"),Color.parseColor("#FFC107"), Color.parseColor("#FF5722")});
        //dataSet.setBarRadius(15f);

        BarData barDatatimebar = new BarData(dataSettime);
        barDatatimebar.setBarWidth(0.5f);

        stepbar.setData(barDatatime);
        stepbar.setDrawGridBackground(false);
        stepbar.getDescription().setEnabled(false);
        stepbar.getLegend().setEnabled(false);
        stepbar.setTouchEnabled(false);

        XAxis xAxistimestep = stepbar.getXAxis();
        xAxistimestep.setGranularity(1f);
        xAxistimestep.setDrawAxisLine(false);
        xAxistimestep.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxistimestep.setDrawGridLines(false);
        xAxistimestep.setAxisMaximum(3.5f); // Adjust maximum value on x-axis
        xAxistimestep.setAxisMinimum(-0.5f);

        YAxis leftAxistimestep = stepbar.getAxisLeft();
        leftAxistimestep.setDrawAxisLine(false);
        leftAxistimestep.setDrawGridLines(false);
        leftAxistimestep.setAxisMinimum(0f);
        leftAxistimestep.setAxisMaximum(1000f); // Adjust maximum value on y-axis

        YAxis rightAxistimestep = stepbar.getAxisRight();
        rightAxistimestep.setDrawAxisLine(false);
        rightAxistimestep.setDrawGridLines(false);
        rightAxistimestep.setDrawLabels(false);

        stepbar.invalidate();




        return view;
    }




}