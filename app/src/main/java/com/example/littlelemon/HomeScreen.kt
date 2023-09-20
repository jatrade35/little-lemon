package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val (loggedIn, setLoggedIn) = rememberPreference(booleanPreferencesKey("LoggedIn"), true)
    setLoggedIn(true)
    Column {
        Text(
            text="Patate Poil!!!"

        )
    }
}
