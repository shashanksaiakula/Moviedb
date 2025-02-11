package com.example.themoviedbapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedb.data.remote.model.Person
import com.example.themoviedbapp.data.repository.PeopleRepository
import retrofit2.HttpException
import java.io.IOException

class PeoplePagingSource(
    private val repository: PeopleRepository
) : PagingSource<Int, Person>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val page = params.key ?: 1
        return try {
            val response = repository.getPopularPeople(page)
            val people = response.results

            LoadResult.Page(
                data = people,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (people.isNotEmpty()) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

