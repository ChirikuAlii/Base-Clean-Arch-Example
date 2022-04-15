package id.chirikualii.base_clean_arch_example.arch.data.remote.service

import id.chirikualii.base_clean_arch_example.arch.data.remote.response.DiscoverMovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("discover/movie?api_key=3b980052023e8e6898f4578a6972740b&language=en-US")
    suspend fun getDiscoverMovie() : Response<DiscoverMovieResponse>
}