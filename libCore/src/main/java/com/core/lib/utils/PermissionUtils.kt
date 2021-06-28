package com.core.lib.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtils {
    private fun requestPermissions(o: Any, permissionId: Int, vararg permissions: String) {
        if (o is AppCompatActivity) {
            ActivityCompat.requestPermissions(o, permissions, permissionId)
        }
        if (o is Fragment) {
            o.requestPermissions(permissions, permissionId)
        }
    }

    private fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions(context: Context, vararg permissions: String): Boolean {
        for (permission in permissions) {
            if (!checkPermission(context, permission)) {
                return false
            }
        }
        return true
    }
}
