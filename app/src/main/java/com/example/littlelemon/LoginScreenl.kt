package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonLightGrey
import com.example.littlelemon.ui.theme.LittleLemonYellow
import com.example.littlelemon.ui.theme.karlaFamily
import kotlinx.coroutines.launch
import androidx.compose.ui.text.font.FontWeight.Companion as FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController ?, appDatabase: AppDatabase ?) {
    val coroutineScope = rememberCoroutineScope()

    val (loggedUser, setLoggedUser) = rememberPreference(stringPreferencesKey("LoggedUser"), "")
    val (_, setLoggedUserPictureId) = rememberPreference(intPreferencesKey("LoggedUserPictureId"), R.drawable.profile_blank)
    val (_, setLoggedUserFistName) = rememberPreference(stringPreferencesKey("LoggedUserFirstName"), "")
    val (_, setLoggedUserLastName) = rememberPreference(stringPreferencesKey("LoggedUserLastName"), "")

    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val (authenticationError, setAuthenticationError) = remember { mutableStateOf(false) }

    val (init, setInit) = remember { mutableStateOf( true ) }
    if(init && loggedUser != ""){
        setInit(false)
        navController!!.navigate(Home.route)
    }
    Column(
        modifier = Modifier
            .background(LittleLemonLightGrey)
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
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
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
                    color = Color.DarkGray)
            },
            colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = Color.Transparent)
        )
        TextField(
            modifier = Modifier
                .padding(start = 20.dp, top = 50.dp, end = 20.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
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
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {setPasswordVisible(!passwordVisible)}){
                    Icon(imageVector  = image, description)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent)
        )
        ClickableText(
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF000000)
            ),
            onClick={
                //navController!!.navigate(PasswordRecovery.route)
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
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = LittleLemonYellow,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.DarkGray
            ),
            onClick = {
                coroutineScope.launch {
                    authenticate(
                        appDatabase = appDatabase!!,
                        email = email,
                        password = password,
                        navController = navController!!,
                        setLoggedUser = setLoggedUser,
                        setLoggedUserPictureId = setLoggedUserPictureId,
                        setAuthenticationError = setAuthenticationError,
                        setLoggedUserFirstName = setLoggedUserFistName,
                        setLoggedUserLastName = setLoggedUserLastName
                    )
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
                    setLoggedUser("")
                    setLoggedUserFistName("")
                    setLoggedUserLastName("")
                    setLoggedUserPictureId(R.drawable.profile_blank)
                    navController!!.navigate(Profile.route)
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
                            setAuthenticationError(false)
                        }){
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

suspend fun authenticate(
    appDatabase: AppDatabase,
    email:String,
    password:String,
    navController: NavHostController,
    setLoggedUser: (String) -> Unit,
    setAuthenticationError: (Boolean) -> Unit,
    setLoggedUserPictureId: (Int) -> Unit,
    setLoggedUserFirstName: (String) -> Unit,
    setLoggedUserLastName: (String) -> Unit) {

    val account = appDatabase.AccountDao().getAccount(email)
    if(account != null && account.email == email && account.password == password) {
        setLoggedUser(account.email)
        setLoggedUserPictureId(account.profilePictureId)
        setLoggedUserFirstName(account.firstName)
        setLoggedUserLastName(account.lastName)
        navController.navigate(Home.route)
    }
    else {
        setAuthenticationError(true)
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = null, appDatabase = null)
}

