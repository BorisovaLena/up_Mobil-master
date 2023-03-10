package com.example.up_mobil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("user/login")
    Call<MaskUsers> newUser(@Body ModelUser modelUser);
}
