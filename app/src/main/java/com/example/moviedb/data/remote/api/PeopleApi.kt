package com.example.moviedb.data.remote.api

import com.example.moviedb.BuildConfig
import com.example.moviedb.data.remote.model.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApi {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PeopleResponse

}
