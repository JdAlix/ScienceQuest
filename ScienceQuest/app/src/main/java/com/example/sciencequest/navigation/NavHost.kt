package com.example.sciencequest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sciencequest.view.AccountScreen
import com.example.sciencequest.view.HomeScreen


@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(goToAccount = {
                navController.navigate("account")
            },
            goToHome = {
                navController.navigate("home")
            }
            )
        }

        composable(route = "account") {
            AccountScreen(goToAccount = {
                navController.navigate("account")
            },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }
    }
}