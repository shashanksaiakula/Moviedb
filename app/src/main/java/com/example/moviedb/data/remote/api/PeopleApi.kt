package com.example.moviedb.data.remote.api

import com.example.moviedb.BuildConfig
import com.example.moviedb.data.remote.model.PeopleResponse
import com.example.moviedb.data.remote.model.PersonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PeopleResponse

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PersonDetails

    @GET("search/person")
    suspend fun searchPeople(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): PeopleResponse
}
