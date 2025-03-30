package com.example.baitap8;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap8.adapter.IconAdapter;
import com.example.baitap8.model.IconModel;

import java.util.ArrayList;
import java.util.List;

public class IndicatorSearchActivity extends AppCompatActivity {
    List<IconModel> arrayList1;
    IconAdapter iconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator_search);

        RecyclerView rcIcon = findViewById(R.id.rcIcon);
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.baseline_chat_24, "Bieu"));
        arrayList1.add(new IconModel(R.drawable.baseline_camera_alt_24, "tuong"));
        arrayList1.add(new IconModel(R.drawable.baseline_call_24, "trangchu"));


//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,
//                GridLayoutManager.HORIZONTAL, false);
        // Khởi tạo LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcIcon.setLayoutManager(layoutManager);

        iconAdapter = new IconAdapter(getApplicationContext(), arrayList1);
        rcIcon.setAdapter(iconAdapter);
        rcIcon.addItemDecoration(new LinePagerIndicatorDecoration(this));

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });

    }
    public static class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
        private final int colorActive = 0xFF0000FF; // Màu xanh dương
        private final int colorInactive = 0x660000FF; // Xanh nhạt
        private final float DP;

        private final Paint paint = new Paint();
        private final float indicatorHeight;
        private final float indicatorItemLength;
        private final float indicatorItemPadding;


        public LinePagerIndicatorDecoration(Activity activity) {
            DP = activity.getResources().getDisplayMetrics().density; //

            indicatorHeight = 16 * DP;
            indicatorItemLength = 8 * DP;
            indicatorItemPadding = 4 * DP;

            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(2 * DP);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int itemCount = parent.getAdapter().getItemCount();

            float totalLength = indicatorItemLength * itemCount;
            float paddingBetweenItems = Math.max(0, itemCount - 1) * indicatorItemPadding;
            float indicatorTotalWidth = totalLength + paddingBetweenItems;
            float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;
            float indicatorPosY = parent.getHeight() - indicatorHeight / 2F;

            drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount);

            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            int activePosition = layoutManager.findFirstVisibleItemPosition();
            if (activePosition == RecyclerView.NO_POSITION) {
                return;
            }

            drawActiveIndicator(canvas, indicatorStartX, indicatorPosY, activePosition);
        }

        private void drawInactiveIndicators(Canvas canvas, float startX, float posY, int itemCount) {
            paint.setColor(colorInactive);
            float itemWidth = indicatorItemLength + indicatorItemPadding;

            for (int i = 0; i < itemCount; i++) {
                float x = startX + i * itemWidth;
                canvas.drawCircle(x, posY, indicatorItemLength / 2F, paint);
            }
        }

        private void drawActiveIndicator(Canvas canvas, float startX, float posY, int highlightPosition) {
            paint.setColor(colorActive);
            float itemWidth = indicatorItemLength + indicatorItemPadding;
            float highlightX = startX + highlightPosition * itemWidth;
            canvas.drawCircle(highlightX, posY, indicatorItemLength / 2F, paint);
        }
    }
    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            iconAdapter.setListenerList(list);
        }
    }
}