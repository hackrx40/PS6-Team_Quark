package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.drive_fit_.R;
import com.example.drive_fit_.modelClass.SpeedLimitResponse;
import com.example.drive_fit_.utils.ApiClient;
import com.example.drive_fit_.utils.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartDrive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_drive);

        double latitude = 18.687516044760738; // Replace with your latitude
        double longitude = 73.71988688274719; // Replace with your longitude

        String apiKey = "AqTzJNBoktCvRdDUNRqy71p7d00xw2-LOoTwvqSZbOWZMvnO-veTXCfNDpEJCtCn";
        String points = "18.687508,73.719880;18.677573,73.723903";
        boolean includeSpeedLimit = true;
        String speedUnit = "KPH";
        String travelMode = "driving";

        ApiService apiService = ApiClient.getApiService();
        Call<SpeedLimitResponse> call = apiService.getData(points, includeSpeedLimit, speedUnit, travelMode, apiKey);


        call.enqueue(new Callback<SpeedLimitResponse>() {
            @Override
            public void onResponse(Call<SpeedLimitResponse> call, Response<SpeedLimitResponse> response) {
                if (response.isSuccessful()) {
                    SpeedLimitResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        float responseData = apiResponse.getResponseData();
                        Log.d("network",apiResponse+"");
                        Toast.makeText(getApplicationContext(),responseData+"",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle null response
                    }
                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<SpeedLimitResponse> call, Throwable t) {
                Log.d("network",t.toString());
            }
        });

    }

}