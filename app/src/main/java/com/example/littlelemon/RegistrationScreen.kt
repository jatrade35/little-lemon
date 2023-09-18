package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.HighlightLightGrey
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.PrimaryGreen
import com.example.littlelemon.ui.theme.karlaFamily
import androidx.compose.ui.text.font.FontWeight.Companion as FontWeight

@Composable
fun RegistrationScreen(navController: NavHostController ?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(HighlightLightGrey),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(95.dp)
                .fillMaxWidth()
                .padding(
                    start = 0.dp,
                    top = 20.dp,
                    end = 0.dp,
                    bottom = 30.dp
                ),
        )
        // if Onboarding has not yet been done
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(PrimaryGreen),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                fontFamily = karlaFamily,
                fontSize = 25.sp,
                text = "Let's get to know you",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                color = HighlightLightGrey,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            fontFamily = karlaFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = "Personal information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 40.dp, end = 10.dp, bottom = 50.dp),
            color = Color(0xFF000000),
        )
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    LittleLemonTheme {
        RegistrationScreen(null)
    }
}