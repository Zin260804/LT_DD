package com.example.myapp;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtKetQua;
    EditText txtNhap;
    Button btnInHoa;
    Button btnXuLi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtKetQua = findViewById(R.id.txtKetQua);
        txtNhap = findViewById(R.id.txtInput);
        btnInHoa = findViewById(R.id.btnInHoa);
        btnXuLi = findViewById(R.id.btnXuli);

        btnXuLi.setOnClickListener(view -> {
            handleRandomArray();
        });
        btnInHoa.setOnClickListener(view -> {
            handleReverseAndUppercase();
        });
    }
    private void handleRandomArray() {
        Random random = new Random();
        int size = random.nextInt(20) + 1; // Kích thước từ 1 đến 20
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100); // Giá trị từ 0 đến 99
        }
        StringBuilder oddNumbers = new StringBuilder("Số lẻ: ");
        StringBuilder evenNumbers = new StringBuilder("Số chẵn: ");
        for (int num : array) {
            if (num % 2 == 0) {
                evenNumbers.append(num).append(" ");
            } else {
                oddNumbers.append(num).append(" ");
            }
        }
        String result = oddNumbers.toString().trim() + "\n" + evenNumbers.toString().trim();
        txtKetQua.setText(result);
    }

    private void handleReverseAndUppercase() {
        String input = txtNhap.getText().toString().trim();

        if (input.isEmpty() || input.trim().equals("")) {
            txtKetQua.setText("Vui lòng nhập một câu!");
            return;
        }
        String[] words = input.split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        String result = reversed.toString().trim().toUpperCase();
        txtKetQua.setText(result);
    }
}
