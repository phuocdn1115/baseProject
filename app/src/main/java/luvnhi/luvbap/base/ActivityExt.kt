package luvnhi.luvbap.base

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions

fun Context.checkPermissions(permissions: Array<String>): Array<String> {
    val array = mutableListOf<String>()
    for (p in permissions) {
        if (PackageManager.PERMISSION_GRANTED != packageManager.checkPermission(p, packageName))
            array.add(p)
    }
    return array.toTypedArray()
}

fun AppCompatActivity.requestPermissions(code: Int, permissions: Array<String>): Boolean {
    val ls = checkPermissions(permissions)
    return if (ls.isNotEmpty()) {
        requestPermissions(this, ls, code)
        false
    } else {
        true
    }
}

fun AppCompatActivity.requestPermissionStorage(code: Int): Boolean {
    val ls = this.checkPermissions(
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            storagePermissionSDK33()
        else storagePermission()
    )
    return if (ls.isNotEmpty()) {
        this.requestPermissions(ls, code)
        false
    } else {
        true
    }
}

val Context.checkPermissionStorage: Boolean
    get() = checkPermissions(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        storagePermissionSDK33()
    else storagePermission()
    ).isEmpty()

fun storagePermission() = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
fun storagePermissionSDK33() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES, android.Manifest.permission.READ_MEDIA_VIDEO, android.Manifest.permission.READ_MEDIA_AUDIO)
} else {
    storagePermission()
}


val IntArray.permissionGranted: Boolean get() = !any { it != PackageManager.PERMISSION_GRANTED }