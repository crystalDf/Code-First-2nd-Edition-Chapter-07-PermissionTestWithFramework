package com.star.permissiontestwithframework;


import java.util.List;

public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermissions);

}
