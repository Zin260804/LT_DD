package vn.iotstar.baitaptuan7_vidukhac.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import vn.iotstar.baitaptuan7_vidukhac.R;

public class AsynctaskActivity extends AppCompatActivity {
    Button btnStart;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo tiến trình
                // Truyền Activity chính là AsynctaskActivity vào tiến trình
                myAsyncTask = new MyAsyncTask(AsynctaskActivity.this);
                // Gọi hàm execute để kích hoạt tiến trình
                myAsyncTask.execute();
            }
        });
    }
}