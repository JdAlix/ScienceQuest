package fr.iut.sciencequest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.iut.sciencequest.view.AccountScreen
import fr.iut.sciencequest.view.HomeScreen
import fr.iut.sciencequest.view.LoginScreen


@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "login"
    ) {
        composable(route = "home") {
            HomeScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }

        composable(route = "account") {
            AccountScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }

        composable(route = "login") {
            LoginScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }
    }
}