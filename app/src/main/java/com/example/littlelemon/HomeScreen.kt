package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val (loggedUser, _ ) = rememberPreference(stringPreferencesKey("LoggedUser"), "")
    Column {
        Text(
            text="Patate Poil!!!"

        )
    }
}
