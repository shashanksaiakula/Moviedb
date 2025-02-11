package com.example.themoviedbapp.presentation.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.themoviedbapp.data.paging.PeoplePagingSource
import com.example.themoviedbapp.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    val people = Pager(PagingConfig(pageSize = 20)) {
        PeoplePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}

