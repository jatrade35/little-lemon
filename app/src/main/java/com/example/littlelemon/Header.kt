package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonLightGrey

@Composable
fun Header(navController: NavController) {
    Column(
        modifier = Modifier
            .background(LittleLemonLightGrey)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        val (loggedUser, _) = rememberPreference(stringPreferencesKey("LoggedUser"), "")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    top = 20.dp,
                    end = 10.dp,
                    bottom = 30.dp
                ),
            ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(40.dp)
                    .width(200.dp)
                    .align(Alignment.Center)
                    .clickable {
                        if(loggedUser != "") {
                            navController.navigate(Home.route)
                        }
                    }
            )
        }
        Divider(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp),
            thickness = 1.dp, color = Color.LightGray)
    }
}

