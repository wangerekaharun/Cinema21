package com.gibsoncodes.movieapp.di

import com.gibsoncodes.movieapp.network.RemoteDataSource
import com.gibsoncodes.movieapp.repository.MoviesRepository
import com.gibsoncodes.movieapp.repository.ShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    @ActivityRetainedScoped
    fun provideMovieRepository(remoteDataSource: RemoteDataSource): MoviesRepository {
        return MoviesRepository(remoteDataSource)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideShowRepository(remoteDataSource: RemoteDataSource): ShowsRepository {
        return ShowsRepository(remoteDataSource)
    }

}