package com.example.encuestacompost.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encuestacompost.ui.main.MainScreen
import com.example.encuestacompost.ui.signin.SigInViewModel
import com.example.encuestacompost.ui.signin.SignInScreen
import com.example.encuestacompost.ui.splash.SplashScreen

@Composable
fun AppNavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(
                navigateToSignIn = { navController.navigate(SignIn) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<SignIn> {
            SignInScreen(
                viewModel = SigInViewModel(LocalContext.current),
                navigateToMain = { navController.navigate(Main) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable<Main> {
            MainScreen()
        }

    }
}