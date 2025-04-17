package com.example.baitap9.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.baitap9.R;
import com.example.baitap9.User;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    TextView tvId, tvUsername, tvFullName, tvEmail, tvGender;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AnhXa();

        // ✅ Nhận User từ Intent (truyền từ UploadImageActivity)
        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            tvId.setText(String.valueOf(user.getId()));
            tvUsername.setText(user.getUsername());
            tvFullName.setText(user.getFname());
            tvEmail.setText(user.getEmail());
            tvGender.setText(user.getGender());

            Glide.with(this)
                    .load(user.getImages())
                    .placeholder(R.drawable.ic_person) // ảnh mặc định nếu chưa có
                    .into(imgProfile);
        }

        imgProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UploadImageActivity.class);
            startActivity(intent);
        });
    }

    private void AnhXa() {
        imgProfile = findViewById(R.id.imgProfile);
        tvId = findViewById(R.id.tvId);
        tvUsername = findViewById(R.id.tvUsername);
        tvFullName = findViewById(R.id.tvFullName);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        btnLogout = findViewById(R.id.btnLogout);
    }
}