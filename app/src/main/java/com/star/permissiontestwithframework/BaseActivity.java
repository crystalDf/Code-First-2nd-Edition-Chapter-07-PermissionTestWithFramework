package com.star.permissiontestwithframework;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;

    private static PermissionListener sPermissionListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(this);
    }

    public static void requestRuntimePermissions(String[] permissions,
                                                 PermissionListener listener) {

        Activity activity = ActivityCollector.getTopActivity();

        sPermissionListener = listener;

        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
        } else {
            sPermissionListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:

                if (grantResults.length > 0) {

                    List<String> deniedPermissions = new ArrayList<>();

                    for (int i = 0; i < grantResults.length; i++) {

                        int grantResult = grantResults[i];
                        String permission = permissions[i];

                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }

                    if (deniedPermissions.isEmpty()) {
                        sPermissionListener.onGranted();
                    } else {
                        sPermissionListener.onDenied(deniedPermissions);
                    }

                }

                break;
            default:
                break;
        }
    }
}
