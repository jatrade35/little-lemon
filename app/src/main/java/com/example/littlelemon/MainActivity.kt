package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    val authentications = mapOf("jatrade35@hotmail.com" to "ksw12ot8", "lindmar67@gmail.com" to "PapaDenis1")
    //PreferedSharedMemory Map(username,password)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val startDestination = if(authentications.isEmpty()) Onboarding.route else Login.route
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startDestination) {
                    composable(Onboarding.route) {
                        OnboardingScreen(navController)
                    }
                    composable(Home.route) {
                        HomeScreen(navController)
                    }
                    composable(PasswordRecovery.route) {
                        PasswordRecoveryScreen(navController)
                    }
                    composable(Registration.route) {
                        RegistrationScreen(navController)
                    }
                    composable(Login.route) {
                        LoginScreen(navController)
                    }
                    composable(Profile.route) {
                        ProfileScreen(navController)
                    }
                }
                navController.navigate(startDestination)
            }
        }
    }
}
