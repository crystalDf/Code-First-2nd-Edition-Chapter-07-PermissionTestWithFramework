package com.star.permissiontestwithframework;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
//        requestRuntimePermissions(new String[]{
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.CAMERA},
//                new PermissionListener() {
//            @Override
//            public void onGranted() {
//                Toast.makeText(MainActivity.this, "All permissions granted", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onDenied(List<String> deniedPermissions) {
//                for (String permission : deniedPermissions) {
//                    Toast.makeText(MainActivity.this, "permission denied: " + permission,
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        Util.test();
    }

}
