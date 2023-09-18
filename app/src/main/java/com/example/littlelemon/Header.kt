package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.HighlightLightGrey

@Composable
fun Header(loggedIn: Boolean) {
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
    }
}

@Preview
@Composable
fun HeaderPreview() {
    Header(loggedIn = false)
}
