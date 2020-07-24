package com.gibsoncodes.movieapp.network

import com.gibsoncodes.movieapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl= originalRequest.url
        val newUrl:HttpUrl= originalUrl.newBuilder().addQueryParameter("api_key",
        BuildConfig.API_KEY).build()
        Timber.d("New url: $newUrl")
       return chain.proceed(originalRequest.newBuilder().url(newUrl).build())
    }
}