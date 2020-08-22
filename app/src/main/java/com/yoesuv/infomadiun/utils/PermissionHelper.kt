package com.yoesuv.infomadiun.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

fun checkGrantedPermission(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

fun requestAppPermission(context: Context, permission: String, onGranted:() -> Unit) {
    Dexter.withContext(context)
        .withPermission(permission)
        .withListener(object: PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                onGranted()
            }
            override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, token: PermissionToken?) {
                token?.continuePermissionRequest()
            }
            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                response?.apply {
                    if (isPermanentlyDenied) {
                        openAppSettings(context)
                    }
                }
            }
        }).check()
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.data = uri
    context.startActivity(intent)
}