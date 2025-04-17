package com.example.baitap10.bai3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baitap10.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class UploadVideoActivity extends AppCompatActivity {
    StorageReference storageReference;
    LinearProgressIndicator progressIndicator;
    Uri video;
    MaterialButton selectVideo, uploadVideo;
    ImageView imageView;
    EditText textTitle, textDesc;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    uploadVideo.setEnabled(true);
                    video = result.getData().getData();
                    Glide.with(UploadVideoActivity.this).load(video).into(imageView);
                }
            } else {
                Toast.makeText(UploadVideoActivity.this, "Please select a video", Toast.LENGTH_SHORT).show();
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_upload_video);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FirebaseApp.initializeApp(this);
        storageReference = FirebaseStorage.getInstance().getReference();

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.imageView);
        progressIndicator = findViewById(R.id.process);
        selectVideo = findViewById(R.id.selectVideo);
        uploadVideo = findViewById(R.id.uploadVideo);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("videos");

        textTitle = findViewById(R.id.textTitle);
        textDesc = findViewById(R.id.textDesc);

        selectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                activityResultLauncher.launch(intent);
            }
        });

        uploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadVideo(video);
            }
        });
    }

    private void uploadVideo(Uri uri) {
        if (uri == null) return;

        String title = textTitle.getText().toString().trim();
        String desc = textDesc.getText().toString().trim();
        String userEmail = auth.getCurrentUser() != null ? auth.getCurrentUser().getEmail() : "anonymous";
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : "unknown";

        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ tiêu đề và mô tả", Toast.LENGTH_SHORT).show();
            return;
        }

        progressIndicator.setVisibility(View.VISIBLE);
        StorageReference reference = storageReference.child("videos/" + UUID.randomUUID().toString());

        reference.putFile(uri).addOnSuccessListener(taskSnapshot ->
                reference.getDownloadUrl().addOnSuccessListener(videoUri -> {
                    // Tạo đối tượng video
                    String videoId = databaseReference.push().getKey();  // Tạo ID ngẫu nhiên
                    VideoModel3 videoModel = new VideoModel3(
                            title,
                            desc,
                            videoUri.toString(),
                            userEmail,
                            userId,
                            0, // likes
                            0 // dislikes
                    );

                    databaseReference.child(videoId).setValue(videoModel)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(this, "Video uploaded & saved!", Toast.LENGTH_SHORT).show();
                                progressIndicator.setVisibility(View.GONE);
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Failed to save video info!", Toast.LENGTH_SHORT).show();
                                progressIndicator.setVisibility(View.GONE);
                            });
                })
        ).addOnFailureListener(e -> {
            Toast.makeText(this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            progressIndicator.setVisibility(View.GONE);
        });
    }

}