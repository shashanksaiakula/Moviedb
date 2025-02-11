package com.example.themoviedbapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.LoadState
import com.example.moviedb.presentation.components.PersonItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController?) {
    val people = viewModel.people.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Famous People") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(people.itemCount) { index ->
                    val person = people[index]
                    person?.let { PersonItem(it, navController) }
                }

                // Infinite Scroll Handling
                when {
                    people.loadState.refresh is LoadState.Loading -> {
                        item { LoadingView() }
                    }
                    people.loadState.append is LoadState.Loading -> {
                        item { LoadingView() }
                    }
                    people.loadState.refresh is LoadState.Error -> {
                        item { ErrorView((people.loadState.refresh as LoadState.Error).error) }
                    }
                    people.loadState.append is LoadState.Error -> {
                        item { ErrorView((people.loadState.append as LoadState.Error).error) }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(error: Throwable) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: ${error.message}", color = MaterialTheme.colorScheme.error)
    }
}
