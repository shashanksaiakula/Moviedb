package com.example.themoviedbapp.navigation

sealed class AppNavigationSealed(val route: String) {
    object HomeScreen : AppNavigationSealed("home")
    object PersonDetails : AppNavigationSealed("details/{id}")
}