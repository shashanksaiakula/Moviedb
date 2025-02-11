package com.example.themoviedbapp.presentation.screens.personDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.remote.model.PersonDetails
import com.example.themoviedbapp.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {
    private val _personDetails = MutableLiveData<PersonDetails>()
    val personDetails: LiveData<PersonDetails> = _personDetails

    fun fetchPersonDetails(personId: Int) {
        viewModelScope.launch {
            try {
                val details = repository.getPersonDetails(personId)
                _personDetails.postValue(details)
            } catch (e: Exception) {
                Log.e("PersonDetails", "Error fetching details", e)
            }
        }
    }
}
