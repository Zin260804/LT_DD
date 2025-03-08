package com.example.btap5;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.btap5.databinding.ActivityMainBinding;
import com.example.btap5.model.User;

public class MainActivity extends AppCompatActivity {
    private User userModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userModel = new User("Huu","Vinh");
        binding.setUser(userModel);
    }
}