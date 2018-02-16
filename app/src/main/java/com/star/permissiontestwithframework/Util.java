package com.star.permissiontestwithframework;


import android.Manifest;
import android.app.Activity;
import android.widget.Toast;

import java.util.List;

public class Util {

    public static void test() {

        final Activity activity = ActivityCollector.getTopActivity();

        BaseActivity.requestRuntimePermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Toast.makeText(activity, "All permissions granted", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {
                        for (String permission : deniedPermissions) {
                            Toast.makeText(activity, "permission denied: " + permission,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
