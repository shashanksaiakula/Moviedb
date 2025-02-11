package com.example.themoviedbapp.data.repository

import com.example.moviedb.data.remote.api.PeopleApi
import com.example.moviedb.data.remote.model.PersonDetails
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val api: PeopleApi) {
    suspend fun getPopularPeople(page: Int) = api.getPopularPeople(page)
    suspend fun getPersonDetails(personId: Int):
            PersonDetails {
        return api.getPersonDetails(personId)
    }
    suspend fun searchPeople(quary : String) = api.searchPeople(quary)
}

