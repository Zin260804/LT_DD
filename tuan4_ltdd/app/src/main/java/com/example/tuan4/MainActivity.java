package com.example.tuan4;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mainLayout;
    private Switch backgroundSwitch;

    // Danh sách các hình nền (đặt các ảnh trong thư mục res/drawable)
    private final int[] backgrounds = {
            R.drawable.background1,
            R.drawable.background2,
            R.drawable.background3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundSwitch = findViewById(R.id.switch1);

        // Tham chiếu tới layout chính (đảm bảo ID đúng)
        mainLayout = findViewById(R.id.main);

        // Kiểm tra xem mainLayout có bị null không trước khi đổi nền
        if (mainLayout != null) {
            changeBackground();
        }
        backgroundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> changeBackground());
    }

    private void changeBackground() {
        // Lấy ngẫu nhiên một hình nền từ danh sách
        int randomIndex = new Random().nextInt(backgrounds.length);
        mainLayout.setBackgroundResource(backgrounds[randomIndex]);
    }
}
