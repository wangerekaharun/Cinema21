package com.gibsoncodes.movieapp.di

import com.gibsoncodes.movieapp.BuildConfig
import com.gibsoncodes.movieapp.network.HttpInterceptor
import com.gibsoncodes.movieapp.network.MovieService
import com.gibsoncodes.movieapp.network.RemoteDataSource
import com.skydoves.sandwich.coroutines.CoroutinesDataSourceCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HttpInterceptor()).build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesDataSourceCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieClient(movieService: MovieService): RemoteDataSource {
        return RemoteDataSource(movieService)
    }
}