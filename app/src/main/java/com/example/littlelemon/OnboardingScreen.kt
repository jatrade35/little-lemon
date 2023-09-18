package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column {
        Header(loggedIn = false)
        Divider(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp),
            thickness = 1.dp, color = Color.LightGray)
    }
}
