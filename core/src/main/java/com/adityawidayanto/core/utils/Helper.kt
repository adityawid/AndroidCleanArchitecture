package com.adityawidayanto.core.utils

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log

fun getStringMetadata(context: Context, name: String): String? {
    try {
        val appInfo = context.packageManager.getApplicationInfo(
            context.packageName, PackageManager.GET_META_DATA
        )
        if (appInfo.metaData != null) {
            return appInfo.metaData.getString(name)
        }
    } catch (e: PackageManager.NameNotFoundException) {
        // if we can’t find it in the manifest, just return null
        Log.e("Str Meta Data", e.message.toString())
    }

    return null
}