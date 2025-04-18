package com.example.baitap10.Vidu2;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baitap10.APIService;
import com.example.baitap10.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private VideosAdapter videosAdapter;
    private List<VideoModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.vpager);
        list = new ArrayList<>();

        //getVideos();
    }

//    private void getVideos() {
//        APIService.serviceapi.getVideos().enqueue(new Callback<MessageVideoModel>() {
//            @Override
//            public void onResponse(Call<MessageVideoModel> call, Response<MessageVideoModel> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    list = response.body().getResult(); // Lấy danh sách video từ response
//                    videosAdapter = new VideosAdapter(getApplicationContext(), list);
//                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
//                    viewPager2.setAdapter(videosAdapter);
//                } else {
//                    Log.e("TAG", "Dữ liệu không hợp lệ hoặc null");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MessageVideoModel> call, Throwable t) {
//                Log.e("TAG", "Lỗi kết nối: " + t.getMessage());
//            }
//        });
//    }
}
