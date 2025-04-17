package com.example.baitap9;

import android.os.Build;
import android.Manifest;

import androidx.annotation.RequiresApi;

public class PermissionUtils {
    // Quyền cho Android < 13
    public static final String[] storage_permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    // Quyền cho Android >= 13 (API 33 - TIRAMISU)
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String[] storage_permissions_33 = {
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };
}
