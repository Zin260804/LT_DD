package com.example.tuan5_ltdd;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuan5_ltdd.adapter.MonhocAdapter;
import com.example.tuan5_ltdd.adapter.SongApdapter;
import com.example.tuan5_ltdd.model.MonHoc;
import com.example.tuan5_ltdd.model.SongModel;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SongApdapter mSongApdapter;
    ArrayList<SongModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv_songs);

        arrayList = new ArrayList<>();
        arrayList.add(new SongModel("60696", "Em Gái Mưa", "Mưa trôi cả bầu trời nắng...", "Hương Tràm"));
        arrayList.add(new SongModel("68701", "Nơi Này Có Anh", "Em có biết anh nhớ em nhiều lắm...", "Sơn Tùng M-TP"));
        arrayList.add(new SongModel("60650", "Có Chàng Trai Viết Lên Cây", "Có chàng trai viết lên cây...", "Phan Mạnh Quỳnh"));
        arrayList.add(new SongModel("60610", "Hồng Nhan", "Cuộc đời con gái chẳng giống con trai...", "Jack"));
        arrayList.add(new SongModel("68656", "Sóng Gió", "Hồng trần trên đôi cánh tay...", "Jack & K-ICM"));
        arrayList.add(new SongModel("68685", "Bông Hoa Đẹp Nhất", "Em là bông hoa đẹp nhất trong đời anh...", "Quân A.P"));
        arrayList.add(new SongModel("60752", "Chạy Ngay Đi", "Chạy ngay đi trước khi mọi điều dần tồi tệ hơn...", "Sơn Tùng M-TP"));
        arrayList.add(new SongModel("60608", "Anh Thương Em Nhất Mà", "Dù rằng em muốn xa anh, anh thương em nhất mà...", "Lã."));
        arrayList.add(new SongModel("68603", "Lạc Trôi", "Nước mắt ta rơi giữa trời...", "Sơn Tùng M-TP"));
        arrayList.add(new SongModel("68720", "Đau Nhất Là Lặng Im", "Anh chẳng thể nói ra lời yêu em...", "Erik"));
        arrayList.add(new SongModel("68856", "Quên Anh Là Điều Em Không Thể - Remix", "Cần thêm bao lâu để em quên đi niềm đau...", "Thiên Ngôn"));

        mSongApdapter = new SongApdapter(RecyclerViewActivity.this, arrayList);
        recyclerView.setAdapter(mSongApdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}