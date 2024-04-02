package fr.iut.sciencequest.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.iut.sciencequest.ViewModels.KahootViewModel
import fr.iut.sciencequest.ViewModels.PenduViewModel
import fr.iut.sciencequest.ViewModels.ScientifiquesDecouvertsVM
import fr.iut.sciencequest.stub.StubQuestionWithReponses
import fr.iut.sciencequest.view.AccountScreen
import fr.iut.sciencequest.view.HomeScreen
import fr.iut.sciencequest.view.LoginScreen
import fr.iut.sciencequest.view.games.KahootScreen
import fr.iut.sciencequest.view.games.PenduScreen
import fr.iut.sciencequest.view.games.QuiScreen
import fr.iut.sciencequest.view.scientifiques.scientifiqueListeScreen


@Composable
fun NavHost(kahotVM: KahootViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                goToPendu = {
                    navController.navigate("pendu")
                },
                goToKahoot = {
                    kahotVM.lancerPartie()
                    navController.navigate("kahoot")
                },
                goToQui = {
                    navController.navigate("qui")
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

        composable(route = "pendu") {
            PenduScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                }
            )
        }

        composable(route= "kahoot"){
            KahootScreen(  goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                viewModel = kahotVM)
        }

        composable(route= "qui"){
            QuiScreen(goToAccount = {
                navController.navigate("account")
            },
                goToHome = {
                    navController.navigate("home")
                })
        }

        composable(route = "listeScientifiques") {
            scientifiqueListeScreen(goToAccount = {
                    navController.navigate("account")
                },
                goToHome =  {
                    navController.navigate("home")
                }
            )
        }
    }
}