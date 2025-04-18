package vn.iotstar.baitaptuan7_vidukhac.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.iotstar.baitaptuan7_vidukhac.R;


public class ProfileActivity extends AppCompatActivity {

    private TextView textWelcome, textEmail;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Ánh xạ giao diện
        textWelcome = findViewById(R.id.textWelcome);
        textEmail = findViewById(R.id.textEmail);
        btnLogout = findViewById(R.id.btnLogout);

        // Lấy thông tin user từ SharedPrefManager
        User user = SharedPrefManager.getInstance(this).getUser();
        textWelcome.setText("Welcome, " + user.getName());
        textEmail.setText("Email: " + user.getEmail());

        // Sự kiện nhấn nút Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(new Intent(ProfileActivity.this, LoginVolleyActivity.class));
                finish();
            }
        });
    }
}