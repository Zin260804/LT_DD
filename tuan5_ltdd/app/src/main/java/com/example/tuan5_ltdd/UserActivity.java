package com.example.tuan5_ltdd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuan5_ltdd.adapter.CustomApdapter;
import com.example.tuan5_ltdd.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvMultipleViewType;
    private List<Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        rvMultipleViewType = (RecyclerView) findViewById(R.id.rv_multipe_view_type);
        mData = new ArrayList<>();
        mData.add(new UserModel(  "Nguyen Van Nghia", "Quan 11"));
        mData.add(R.drawable.java);
        mData.add("Text 0");
        mData.add("Text 1");
        mData.add(new UserModel( "Nguyen Hoang Minh", "Quan 3"));
        mData.add("Text 2");
        mData.add(R.drawable.c);
        mData.add(R.drawable.php);
        mData.add(new UserModel(  "Pham Nguyen Tam Phu","Quan 10"));
        mData.add("Text 3");
        mData.add("Text 4");
        mData.add(new UserModel( "Tran Van Phuc",  "Quan 1"));
        mData.add(R.drawable.ic_launcher_background) ;
        CustomApdapter adapter = new CustomApdapter(this, mData);
        rvMultipleViewType. setAdapter (adapter);
        rvMultipleViewType. setLayoutManager(new LinearLayoutManager( this));
    }
}