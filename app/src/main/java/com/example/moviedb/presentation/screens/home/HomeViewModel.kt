package com.example.themoviedbapp.presentation.screens.home


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.moviedb.data.remote.model.PeopleResponse
import com.example.themoviedbapp.data.paging.PeoplePagingSource
import com.example.themoviedbapp.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {
    private val _personDetails = MutableLiveData<PeopleResponse?>()
    val personDetails: MutableLiveData<PeopleResponse?> = _personDetails

    val people = Pager(PagingConfig(pageSize = 20)) {
        PeoplePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    fun fetchBySearch(query: String) {
        viewModelScope.launch {
            try {
                val details = repository.searchPeople(query)
                _personDetails.postValue(details)
            } catch (e: Exception) {
                Log.e("PersonDetails", "Error fetching details", e)
            }
        }
    }
    fun clearSearch() {
        _personDetails.postValue(null)
    }
}

