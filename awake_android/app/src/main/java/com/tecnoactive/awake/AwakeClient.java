package com.tecnoactive.awake;


import android.util.TypedValue;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AwakeClient {

    @GET("/api/employees/{id}")
    Call<Employee> employee(@Path("id") String id);

    @GET("/api/login/1")
    Call<Employee> login(@Header("Authorization") String authHeader);

    @GET("/api/directory/1")
    Call<ArrayList<Apartment>> directory();

    @GET("/api/messages/1")
    Call<ArrayList<Message>> messages(@Header("Authorization") String authHeader);

    @GET("/api/challenges/1")
    Call<ArrayList<Work>> works(@Header("Authorization") String authHeader);

    @GET("/api/parameters/1")
    Call<ArrayList<Posponedor>> posponers();

}
