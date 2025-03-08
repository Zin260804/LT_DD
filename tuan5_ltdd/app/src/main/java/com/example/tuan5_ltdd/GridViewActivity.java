package com.example.tuan5_ltdd;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan5_ltdd.adapter.MonhocAdapter;
import com.example.tuan5_ltdd.model.MonHoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    int vitri = -1;  // Biến toàn cục
    GridView gridView;
    EditText editText1;
    Button btnNhap,btnCapnhat;
    FloatingActionButton btnNutXoa;
    ArrayList<MonHoc> arrayList;
    MonhocAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid_view);

        AnhXa();

        adapter = new MonhocAdapter(GridViewActivity.this,
                R.layout.row_monhoc,
                arrayList
        );

        gridView.setAdapter(adapter);
    }
    private void AnhXa() {
        gridView= (GridView) findViewById(R.id.gridview1);
        editText1 = (EditText) findViewById(R.id.editNhap);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapnhat = (Button) findViewById(R.id.btnCapNhat);


        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java));
        arrayList.add(new MonHoc("C++","C++ 1",R.drawable.c));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));
    }
}