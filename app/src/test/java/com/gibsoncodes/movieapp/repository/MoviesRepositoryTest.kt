package com.gibsoncodes.movieapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gibsoncodes.movieapp.MainCoroutinesRule
import com.gibsoncodes.movieapp.network.MovieService
import com.gibsoncodes.movieapp.network.RemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MoviesRepositoryTest {
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var client: RemoteDataSource
    private val service: MovieService = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        client = RemoteDataSource(service)
        moviesRepository = MoviesRepository(client)
    }

    @Test
    fun fetchPopularMovieList() = runBlocking {
        // val mockData = MovieResponse(results=mocking)
    }

}