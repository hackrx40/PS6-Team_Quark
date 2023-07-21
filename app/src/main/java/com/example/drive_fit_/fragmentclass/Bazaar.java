package com.example.drive_fit_.fragmentclass;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drive_fit_.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Bazaar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bazaar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Bazaar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bazaar.
     */
    // TODO: Rename and change types and number of parameters
    public static Bazaar newInstance(String param1, String param2) {
        Bazaar fragment = new Bazaar();
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

    private BarChart barChart;
    private LineChart lineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bazaar, container, false);

        TextView starttrip = v.findViewById(R.id.startrips);

        starttrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getContext(), StepTracking.class);
//                startActivity(i);
            }
        });


        barChart = v.findViewById(R.id.barChart);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 500f));
        entries.add(new BarEntry(1f, 750f));
        entries.add(new BarEntry(2f, 1000f));

        BarDataSet dataSet = new BarDataSet(entries, "Bar Data Set");
        dataSet.setColors(new int[]{Color.parseColor("#CDDC39"),Color.parseColor("#FFC107"), Color.parseColor("#FF5722")});
        //dataSet.setBarRadius(15f);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.5f);

        barChart.setData(barData);

        barChart.setDrawGridBackground(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setTouchEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(3.5f); // Adjust maximum value on x-axis
        xAxis.setAxisMinimum(-0.5f);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(1000f); // Adjust maximum value on y-axis

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);

        barChart.invalidate();



        lineChart = v.findViewById(R.id.lineChart);

        // Create data entries
        List<Entry> entriess = new ArrayList<>();
        entriess.add(new Entry(1f, 100f));
        entriess.add(new Entry(2f, 600f));
        entriess.add(new Entry(3f, 324f));

        // Create a dataset with entries
        LineDataSet dataSe = new LineDataSet(entriess, "Line Data Set");
        dataSe.setColors(ColorTemplate.rgb("#FFEB3B")); // Set line color
        dataSe.setLineWidth(2f); // Set line width
        dataSe.setDrawFilled(true); // Enable filled mode

        // Create a gradient fill
        dataSe.setFillDrawable(getResources().getDrawable(R.drawable.gradient_fill));

        // Create a list of datasets
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSe);

        // Create a LineData object with datasets
        LineData lineData = new LineData(dataSets);

        // Set LineData to the chart
        lineChart.setData(lineData);

        // Customize the appearance of the chart
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);

        XAxis xAxiss = lineChart.getXAxis();
        xAxiss.setGranularity(1f);
        xAxiss.setDrawAxisLine(true);
        xAxiss.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxiss.setDrawGridLines(false);
        xAxiss.setAxisMinimum(1f);
        xAxiss.setAxisMaximum(3f);

        YAxis leftAxiss = lineChart.getAxisLeft();
        leftAxiss.setDrawAxisLine(true);
        leftAxiss.setDrawGridLines(true);
        leftAxiss.setAxisMinimum(0f);
        leftAxiss.setAxisMaximum(1000f);

        YAxis rightAxiss = lineChart.getAxisRight();
        rightAxiss.setEnabled(false);

        lineChart.invalidate(); // Refresh the chart



        return v;
    }
}