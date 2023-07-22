package com.example.drive_fit_.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.drive_fit_.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StartDrive extends AppCompatActivity {

    final OkHttpClient client = new OkHttpClient();
    Context context = this;
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

        try {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    String response = context.run("https://dev.virtualearth.net/REST/v1/Routes/SnapToRoad?points=18.687508,73.719880;18.677573,73.723903&IncludeSpeedLimit=true&speedUnit=KPH&travelMode=driving&key=AqTzJNBoktCvRdDUNRqy71p7d00xw2-LOoTwvqSZbOWZMvnO-veTXCfNDpEJCtCn");
                }
            });

            Log.i("network",response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        ApiService apiService = ApiClient.getApiService();
//        Call<SpeedLimitResponse> call = apiService.getData(points, includeSpeedLimit, speedUnit, travelMode, apiKey);







//        call.enqueue(new Callback<SpeedLimitResponse>() {
//            @Override
//            public void onResponse(Call<SpeedLimitResponse> call, Response<SpeedLimitResponse> response) {
//                Log.i("network",response.toString());
//                if (response.isSuccessful()) {
//                    SpeedLimitResponse apiResponse = response.body();
//                    if (apiResponse != null) {
//                        float responseData = apiResponse.getResponseData();
//                        Log.d("network",apiResponse+"");
//                        Toast.makeText(getApplicationContext(),responseData+"",Toast.LENGTH_SHORT).show();
//                    } else {
//                        // Handle null response
//                    }
//                } else {
//
//                    // Handle API error
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SpeedLimitResponse> call, Throwable t) {
//                Log.d("network",t.toString());
//            }
//        });

    }

}