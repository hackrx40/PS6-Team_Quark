package com.example.drive_fit_.utils;

import com.example.drive_fit_.modelClass.SpeedLimitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("SnapToRoad")
    Call<SpeedLimitResponse> getData(@Query("points") String points,
                                     @Query("IncludeSpeedLimit") boolean includeSpeedLimit,
                                     @Query("speedUnit") String speedUnit,
                                     @Query("travelMode") String travelMode,
                                     @Query("key") String apiKey);
}
