package com.example.themoviedbapp.data.repository

import com.example.moviedb.data.remote.api.PeopleApi
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val api: PeopleApi) {
    suspend fun getPopularPeople(page: Int) = api.getPopularPeople(page)

}

