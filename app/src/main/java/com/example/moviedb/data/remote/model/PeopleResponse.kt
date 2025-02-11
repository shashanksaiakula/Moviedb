package com.example.moviedb.data.remote.model

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int
)
