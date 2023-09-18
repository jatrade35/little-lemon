package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

//Fonts
val markaziTextFamily = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Normal),
    Font(R.font.markazitext_medium, FontWeight.Medium),
    Font(R.font.markazitext_semibold, FontWeight.SemiBold),
    Font(R.font.markazitext_bold, FontWeight.Bold)
)

val karlaFamily = FontFamily(
    Font(R.font.karla_bold, FontWeight.Bold),
    Font(R.font.karla_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.karla_extrabold, FontWeight.ExtraBold),
    Font(R.font.karla_extralight, FontWeight.ExtraLight),
    Font(R.font.karla_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.karla_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.karla_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.karla_light, FontWeight.Light),
    Font(R.font.karla_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_semibold, FontWeight.SemiBold),
    Font(R.font.karla_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = markaziTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)