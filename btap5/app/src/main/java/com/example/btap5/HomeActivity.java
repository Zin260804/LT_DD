package com.example.btap5;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btap5.adapter.ListUserAdapter;
import com.example.btap5.databinding.ActivityHomeBinding;
import com.example.btap5.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ListUserAdapter.OnItemClickListener {
    public ObservableField<String> title = new ObservableField<>();
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        title.set("Ví dụ về Databinding cho Recycle View");
        binding.setHome(this);


        setData();
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        listUserAdapter.setOnItemClickListener(this);
        binding.rcView.setAdapter(listUserAdapter);
    }

    private void setData() {
        List<UserModel> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserModel user = new UserModel();
            user.setFirstName("Huu" + i);
            user.setLastName("Trung" + i);
            userList.add(user);
        }
        listUserAdapter = new ListUserAdapter(userList);
    }
    @Override
    public void itemClick(UserModel user) {
        Toast.makeText(this, "bạn vừa click", Toast.LENGTH_SHORT).show();
    }
}
