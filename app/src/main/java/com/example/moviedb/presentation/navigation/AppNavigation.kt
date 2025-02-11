package com.example.themoviedbapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.themoviedbapp.presentation.screens.home.HomeScreen
import com.example.themoviedbapp.presentation.screens.personDetails.PersonDetailsScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigationSealed.HomeScreen.route){
        composable(AppNavigationSealed.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable("details/{id}") { backStackEntry ->
            val personId = backStackEntry.arguments?.getString("id")
            if (personId != null) {
                PersonDetailsScreen(navController = navController, personId = personId.toInt())
            }

        }
    }
}


