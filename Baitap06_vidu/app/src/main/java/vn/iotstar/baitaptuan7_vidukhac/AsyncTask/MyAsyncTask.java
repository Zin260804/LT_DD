package vn.iotstar.baitaptuan7_vidukhac.AsyncTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.iotstar.baitaptuan7_vidukhac.R;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    // Tạo constructor
    public MyAsyncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    // Implement Method
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Hàm này sẽ chạy đầu tiên
        // Ở đây mình sẽ thông báo trước
        Toast.makeText(contextParent, "Bắt đầu tiến trình", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Hàm được thực hiện trên luồng phụ
        // Hàm này thực hiện các tác vụ không ảnh hưởng đến giao diện
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            // Khi gọi hàm này thì onProgressUpdate sẽ được gọi
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        // Thông qua contextCha để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prbDemo);

        // Vì publishProgress truyền 1 đối số nên values chỉ có 1 phần tử
        int number = values[0];

        // Cập nhật giá trị của ProgressBar
        progressBar.setProgress(number);

        // Đồng thời hiển thị giá trị % lên TextView
        TextView textView = (TextView) contextParent.findViewById(R.id.txtStatus);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Hàm này được thực hiện khi tiến trình kết thúc
        // Ở đây mình thông báo là "Finished" để người dùng biết
        Toast.makeText(contextParent, "Đã hoàn thành, Finished", Toast.LENGTH_SHORT).show();
    }
}