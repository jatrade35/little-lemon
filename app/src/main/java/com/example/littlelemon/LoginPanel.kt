package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.HighlightLightGrey
import com.example.littlelemon.ui.theme.karlaFamily
import androidx.compose.ui.text.font.FontWeight.Companion as FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPanel(navController: NavHostController ?) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val authenticationData: MutableMap<String, String> = mutableMapOf(
                Pair("jatrade35@hotmail.com","ksw12ot8"),
                Pair("lindmar67@gmail.com","PapaDenis1")
    )
    var authenticationError by remember { mutableStateOf(false) }

// Map (username, password) PreferredSharedMemory
    Column(
        modifier = Modifier
            .background(HighlightLightGrey)
            .fillMaxHeight()
    ) {
        Text(
            fontFamily = karlaFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            text = "Sign In To Your Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 40.dp, end = 20.dp, bottom = 50.dp),
            color = Color(0xFF000000)
        )
        TextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = {
                setEmail(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 20.sp,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(
                    text="Email",
                    fontFamily = karlaFamily,
                    fontSize = 20.sp,
                    color = Color.Gray)
            }
        )
        TextField(
            modifier = Modifier
                .padding(start = 20.dp, top = 50.dp, end = 20.dp)
                .fillMaxWidth(),
            value = password,
            onValueChange = {
                setPassword(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 20.sp,
            ),
            placeholder = {
                Text(
                    text="Password",
                    fontFamily = karlaFamily,
                    fontSize = 20.sp,
                    color = Color.Gray)
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            })
        ClickableText(
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF000000)
            ),
            onClick={
                navController!!.navigate(PasswordRecovery.route)
            },
            text = AnnotatedString("Forgot Password?"),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 2.dp, end = 20.dp, bottom = 50.dp),
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                if(authenticationData.containsKey(email) && authenticationData[email] == password){
                    navController!!.navigate(Home.route)
                }
                else
                {
                    authenticationError = true
                }
            }
        ){
                Text(text = "Login")
        }
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 5.dp),
                text = "New to Little Lemon?",
                fontSize = 12.sp,
                )
            ClickableText(
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF000000)
                ),
                onClick = {
                    navController!!.navigate(Registration.route)
                },
                text = AnnotatedString("Create a Free Account Now."),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
            )
        }
        when {
            // ...
            authenticationError -> {
                AlertDialog(
                    onDismissRequest = {},
                    title = {Text("Authentication Failed!")},
                    text = {Text("Something went wrong with authentication. Please verify your login credentials and try again.")},
                    confirmButton = {
                        Button(onClick={
                            authenticationError = false
                        }){
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginPanel(navController = null)
}

