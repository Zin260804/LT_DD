package com.example.baitap8;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.baitap8.adapter.SliderAdapter;
import com.library.foysaltech.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.library.foysaltech.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class SliderViewActivity extends AppCompatActivity {

    private SliderView sliderView;
    private ArrayList<Integer> arrayList;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slider_view);

        sliderView = findViewById(R.id.imageSlider);

        arrayList = new ArrayList<>();
        arrayList.add(R.drawable.baseline_camera_alt_24);
        arrayList.add(R.drawable.baseline_chat_24);
        arrayList.add(R.drawable.baseline_call_24);
        arrayList.add(R.drawable.ic_launcher_foreground);

// Gọi Adapter, truyền dữ liệu
        sliderAdapter = new SliderAdapter(getApplicationContext(), arrayList);
        sliderView.setSliderAdapter(sliderAdapter);

// Thiết lập hiệu ứng và tùy chọn cho Slider
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(getResources().getColor(R.color.red));
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();
        sliderView.setScrollTimeInSec(5);
    }
}