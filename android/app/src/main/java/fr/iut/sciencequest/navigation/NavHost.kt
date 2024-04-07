package fr.iut.sciencequest.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.iut.sciencequest.view.AccountScreen
import fr.iut.sciencequest.view.HomeScreen
import fr.iut.sciencequest.view.LoginScreen
import fr.iut.sciencequest.view.games.kahoot.KahootScreen
import fr.iut.sciencequest.view.games.PenduScreen
import fr.iut.sciencequest.view.games.QuiScreen
import fr.iut.sciencequest.view.games.kahoot.MenuKahoot
import fr.iut.sciencequest.view.games.kahoot.ResultatKahoot
import fr.iut.sciencequest.view.games.kahoot.WaitingScreen
import fr.iut.sciencequest.view.scientifiques.scientifiqueListeScreen
import fr.iut.sciencequest.viewModels.KahootViewModel


@Composable
fun NavHost(kahootVM: KahootViewModel) {
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
                    navController.navigate("kahootMenu")
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

        composable(route= "kahootGame"){
            KahootScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                goToResult = {
                    navController.navigate("kahootResult")
                },
                viewModel = kahootVM
            )
        }

        composable(route = "kahootResult") {
            ResultatKahoot(
                goToAccount = {
                   navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                kahootViewModel = kahootVM
            )
        }

        composable(route= "kahootMenu"){
            MenuKahoot(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                goToWaiting = {
                    kahootVM.createPartie(1,1, listOf(1))
                    navController.navigate("kahootWaiting")
                },
                kahootViewModel = kahootVM
            )
        }

        composable(route = "kahootWaiting") {
            WaitingScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome = {
                    navController.navigate("home")
                },
                goToGame = {
                    navController.navigate("kahootGame")
                },
                kahootViewModel = kahootVM
                )
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
            scientifiqueListeScreen(
                goToAccount = {
                    navController.navigate("account")
                },
                goToHome =  {
                    navController.navigate("home")
                }
            )
        }
    }
}