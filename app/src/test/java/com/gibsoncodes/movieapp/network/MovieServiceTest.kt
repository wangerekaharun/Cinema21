package com.gibsoncodes.movieapp.network

import com.gibsoncodes.movieapp.MainCoroutinesRule
import com.gibsoncodes.movieapp.model.MovieResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.toResponseDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class MovieServiceTest : BaseApi<MovieService>() {
    private lateinit var service: MovieService
    private val client: RemoteDataSource = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createMockService(MovieService::class.java)

    }

    @Throws(IOException::class)
    @Test
    fun fetchPopularMoviesListTest() = runBlocking {
        enqueueResponse("popular_movies.json")
        val dataSource = requireNotNull(
            service.fetchPopularMoviesList().toResponseDataSource()
        )
        val responseBody = requireNotNull(
            dataSource.call?.execute()?.body()
        )
        mockWebServer.takeRequest()
        assertThat(responseBody.results[0].title, `is`("Greyhound"))
        val onResult: (response: ApiResponse<MovieResponse>) -> Unit = {
            /**
             * it.onSuccess{
             *data.results
             * }
             */
            assertThat(it, instanceOf(ApiResponse.Success::class.java))
            val response: MovieResponse = requireNotNull((it as ApiResponse.Success).data)
            assertThat(response.results[0].title, `is`("Greyhound"))
        }
        whenever(client.fetchPopularMoviesList(1, onResult)).thenAnswer {
            val response: (response: ApiResponse<MovieResponse>) -> Unit = it.getArgument(1)
            response(ApiResponse.Success(Response.success(responseBody)))

        }
        client.fetchPopularMoviesList(page = 1, result = onResult)

    }


}