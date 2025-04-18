package vn.iotstar.baitaptuan7_vidukhac.Handler;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import vn.iotstar.baitaptuan7_vidukhac.R;

// Ví dụ 1
public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Tạo luồng chạy ngầm để đợi 2 giây rồi chuyển sang LoginActivity
        new Thread() {
            public void run() {
                int n = 0;
                try {
                    do {
                        if (n >= 2000) {
                            IntroActivity.this.finish();
                            Intent intent = new Intent(IntroActivity.this.getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        Thread.sleep(100);
                        n += 100;
                    } while (true);
                } catch (InterruptedException e) {
                    IntroActivity.this.finish();
                    Intent intent = new Intent(IntroActivity.this.getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } catch (Throwable throwable) {
                    IntroActivity.this.finish();
                    Intent intent = new Intent(IntroActivity.this.getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    throw throwable;
                }
            }
        }.start();
    }
}
