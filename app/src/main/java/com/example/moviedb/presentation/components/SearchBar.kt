package com.example.moviedb.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(searchQuery: TextFieldValue, onSearchQueryChanged: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
        singleLine = true
    )
}