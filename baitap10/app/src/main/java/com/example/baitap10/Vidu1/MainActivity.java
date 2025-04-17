package com.example.baitap10.Vidu1;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baitap10.R;
import com.example.baitap10.bai3.VideoModel3;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private VideosFireBaseAdapter videosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 💥 Dòng này giúp tránh lỗi "FirebaseApp is not initialized"
        com.google.firebase.FirebaseApp.initializeApp(this);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.vpager);
        getVideos();
    }


    private void getVideos() {
        Log.d("FirebaseAdapter", "getVideos() called");

        // Kết nối đến Firebase Database, bảng "videos"
        DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference("videos");

        // Tạo options cho FirebaseRecyclerAdapter
        FirebaseRecyclerOptions<VideoModel3> options =
                new FirebaseRecyclerOptions.Builder<VideoModel3>()
                        .setQuery(mDataBase, VideoModel3.class)
                        .build();

        // Tạo adapter và gán vào ViewPager2
        videosAdapter = new VideosFireBaseAdapter(options);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(videosAdapter);
        Log.d("FirebaseAdapter", "Adapter initialized");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (videosAdapter != null) {
            Log.d("MainActivity", "Adapter bắt đầu lắng nghe dữ liệu");
            videosAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videosAdapter != null) {
            videosAdapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videosAdapter != null) {
            videosAdapter.notifyDataSetChanged();
        }
    }
}
