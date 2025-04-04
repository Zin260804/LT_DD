package com.example.baitap9.retrofit;

import android.os.Message;

import com.example.baitap9.Const;
import com.example.baitap9.ImageUpload;
import com.example.baitap9.UserResponse;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceAPI {

    String BASE_URL = "http://app.iotstar.vn:8081/appfoods/";

    Gson gson = new com.google.gson.GsonBuilder()
            .setDateFormat("yyyy MM dd HH:mm:ss")
            .create();

    ServiceAPI serviceapi = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ServiceAPI.class);

    @Multipart
    @POST("upload.php")
    Call<List<ImageUpload>> upload(
            @Part(Const.MY_USERNAME) RequestBody username,
            @Part MultipartBody.Part avatar
    );

    @Multipart
    @POST("upload1.php")
    Call<Message> upload1(
            @Part(Const.MY_USERNAME) RequestBody username,
            @Part MultipartBody.Part avatar
    );

    @Multipart
    @POST("updateimages.php")
    Call<UserResponse> updateImage(
            @Part("id") RequestBody id,
            @Part MultipartBody.Part images
    );
}
