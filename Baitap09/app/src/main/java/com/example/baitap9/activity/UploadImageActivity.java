package com.example.baitap9.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitap9.PermissionUtils;
import com.example.baitap9.R;
import com.example.baitap9.RealPathUtil;
import com.example.baitap9.User;
import com.example.baitap9.UserResponse;
import com.example.baitap9.retrofit.ServiceAPI;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImageActivity extends AppCompatActivity {

    Button btnChoose, btnUpload;
    ImageView imageViewChoose;
    private Uri mUri;
    private ProgressDialog mProgressDialog;

    public static final int MY_REQUEST_CODE = 101;
    public static final String TAG = UploadImageActivity.class.getName();
    private int userId;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            mUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
                                imageViewChoose.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        AnhXa();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Uploading...");

        userId = getIntent().getIntExtra("id", 5);

        btnChoose.setOnClickListener(v -> CheckPermission());

        btnUpload.setOnClickListener(v -> {
            if (mUri != null && userId != -1) {
                UploadImageToServer();
            } else {
                Toast.makeText(this, "Vui lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        btnChoose = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageViewChoose = findViewById(R.id.imgChoose);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Chọn ảnh"));
    }

    private void CheckPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            requestPermissions(permissions(), MY_REQUEST_CODE);
        }
    }

    public static String[] permissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return PermissionUtils.storage_permissions_33;
        } else {
            return PermissionUtils.storage_permissions;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void UploadImageToServer() {
        mProgressDialog.show();

        String realPath = RealPathUtil.getRealPath(this, mUri);
        File file = new File(realPath);

        RequestBody requestId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(userId));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part partImage = MultipartBody.Part.createFormData("images", file.getName(), requestFile);

        ServiceAPI.serviceapi.updateImage(requestId, partImage).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    Toast.makeText(UploadImageActivity.this, "Upload thành công", Toast.LENGTH_SHORT).show();
                    User user = response.body().getResult();
                    Intent intent = new Intent(UploadImageActivity.this, ProfileActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(UploadImageActivity.this, "Upload thất bại!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(UploadImageActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
