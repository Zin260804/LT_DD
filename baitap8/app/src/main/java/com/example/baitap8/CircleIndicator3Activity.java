package com.example.baitap8;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baitap8.adapter.ImageSliderAdapter;
import com.example.baitap8.adapter.ImagesViewPager2Adapter;
import com.example.baitap8.model.Images;
import com.example.baitap8.model.ImagesSlider;
import com.example.baitap8.model.MessageModel;
import com.example.baitap8.retrofit.APIService;
import com.example.baitap8.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CircleIndicator3Activity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<ImagesSlider> imagesList1;
    APIService apiService;
    ImagesViewPager2Adapter imagesViewPager2Adapter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Autorun cho ViewPager
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }

            // Autorun cho ViewPager2
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_indicator3);

        // Ánh xạ ViewPager2 và CircleIndicator3
        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        // Lấy dữ liệu hình ảnh
        getListImages();



        // Gọi lần đầu
        handler.postDelayed(runnable, 3000);

        // Lắng nghe khi user vuốt ViewPager2 để reset autorun
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
        viewPager2.setPageTransformer(new DepthPageTransformer());

    }

    private void getListImages() {
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.LoadImageSlider(1).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.body() != null && response.body().isSuccess()) {
                    imagesList1 = response.body().getResult();
                    ImageSliderAdapter adapter = new ImageSliderAdapter(CircleIndicator3Activity.this, imagesList1);
                    viewPager2.setAdapter(adapter);
                    circleIndicator3.setViewPager(viewPager2);
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
            }
        });
    }


    public class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) {
                // Off-screen to the left
                view.setAlpha(0f);
            } else if (position <= 0) {
                // Default slide when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);
            } else if (position <= 1) {
                // Fade out
                view.setAlpha(1 - position);

                // Slide transition
                view.setTranslationX(pageWidth * -position);
                view.setTranslationZ(-1f);

                // Scale page down
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                // Off-screen to the right
                view.setAlpha(0f);
            }
        }
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                // Off-screen to the left
                view.setAlpha(0f);
            } else if (position <= 1) {
                // Scale down & fade
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;

                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else {
                // Off-screen to the right
                view.setAlpha(0f);
            }
        }
    }
}