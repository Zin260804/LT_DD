package com.example.tuan4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan4.R;

public class CustomButtonActivity extends AppCompatActivity {
    Button btnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_button);

        btnButton = findViewById(R.id.button3);
        btnButton.setOnClickListener(v -> ShowPopupMenu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSetting:
                // xử lý
                Toast.makeText(this, "Chọn Settings từ menu", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuShare:
                Toast.makeText(this, "Chia sẻ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuLogout:
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuSetting:
                    Toast.makeText(CustomButtonActivity.this, "Bạn đang chọn Setting", Toast.LENGTH_LONG).show();
                    return true;
                case R.id.menuShare:
                    btnButton.setText("Chia sẻ");
                    return true;
                case R.id.menuLogout:
                    Toast.makeText(CustomButtonActivity.this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });

        popupMenu.show();
    }
}
