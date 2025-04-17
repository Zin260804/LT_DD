package com.example.tuan5_ltdd;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tuan5_ltdd.adapter.MonhocAdapter;
import com.example.tuan5_ltdd.model.MonHoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    int vitri = -1;  // Biến toàn cục
    ListView listView;
    EditText editText1;
    Button btnNhap,btnCapnhat;
    FloatingActionButton btnNutXoa;
    ArrayList<MonHoc> arrayList;

    MonhocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview1);
        editText1 = (EditText) findViewById(R.id.editNhap);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnCapnhat = (Button) findViewById(R.id.btnCapNhat);


        arrayList = new ArrayList<>();
        arrayList.add(new MonHoc("Java","Java 1",R.drawable.java));
        arrayList.add(new MonHoc("C++","C++ 1",R.drawable.c));
        arrayList.add(new MonHoc("PHP","PHP 1",R.drawable.php));

        adapter = new MonhocAdapter(ListViewActivity.this,
                R.layout.row_monhoc,
                arrayList
        );
        listView.setAdapter(adapter);
    }
}
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
//            }
//        });

//bắt sự kiện click giữ trên từng dòng của Listview
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                editText1.setText(arrayList.get(i));
//                vitri = i;
//            }
//        });
//
//        btnNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editText1.getText().toString();
//                arrayList.add(name);
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        btnCapnhat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                arrayList.set(vitri, editText1.getText().toString());
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        btnNutXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                arrayList.remove(vitri);
//                adapter.notifyDataSetChanged();
//            }
//        });