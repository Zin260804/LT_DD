package com.example.baitap10;

import com.example.baitap10.Vidu2.MessageVideoModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIService {

    // Cấu hình Gson để parse ngày giờ (nếu cần)
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy MM dd HH:mm:ss")
            .create();

    // Tạo đối tượng Retrofit để gọi API
    APIService serviceapi = new Retrofit.Builder()
            .baseUrl("http://app.iotstar.vn:8081/appfoods/") // Base URL
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    // Gọi file getvideos.php và parse về MessageVideoModel
    @GET("getvideos.php")
    Call<MessageVideoModel> getVideos();
}