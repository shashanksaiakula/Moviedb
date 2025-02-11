package com.example.themoviedbapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.LoadState
import com.example.moviedb.presentation.components.PersonItem
import com.example.moviedb.presentation.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController?) {
    val people = viewModel.people.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isSearchVisible by remember { mutableStateOf(false) }

    val searchResults by viewModel.personDetails.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Famous People") },
                actions = {
                    IconButton(onClick = {
                        isSearchVisible = !isSearchVisible
                        if (!isSearchVisible) {
                            searchQuery = TextFieldValue("")
                            viewModel.clearSearch()
                        }
                    }) {
                        Icon(
                            imageVector = if (isSearchVisible) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (isSearchVisible) {
                SearchBar (searchQuery) { query ->
                    searchQuery = query
                    if (query.text.isNotEmpty()) {
                        viewModel.fetchBySearch(query.text)
                    }
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (isSearchVisible && searchResults?.results?.isNotEmpty() == true) {
                    items(searchResults!!.results) { person ->
                        PersonItem(person, navController)
                    }
                } else {
                    items(people.itemCount) { index ->
                        val person = people[index]
                        person?.let { PersonItem(it, navController) }
                    }
                    // Infinite Scroll Handling
                    people.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { LoadingView() }
                            }
                            loadState.append is LoadState.Loading -> {
                                item { LoadingView() }
                            }
                            loadState.refresh is LoadState.Error -> {
                                item { ErrorView((loadState.refresh as LoadState.Error).error) }
                            }
                            loadState.append is LoadState.Error -> {
                                item { ErrorView((loadState.append as LoadState.Error).error) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(error: Throwable) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${error.message}", color = MaterialTheme.colorScheme.error)
    }
}
