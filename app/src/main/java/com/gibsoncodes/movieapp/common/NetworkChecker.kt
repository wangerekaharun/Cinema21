package com.gibsoncodes.movieapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi

object NetworkChecker {
    @RequiresApi(Build.VERSION_CODES.M)
    fun Context.checkNetwork(): Boolean? {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo?.isConnectedOrConnecting

    }
}