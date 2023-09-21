package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonCharcoal
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonLightGrey
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.LittleLemonYellow
import com.example.littlelemon.ui.theme.karlaFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController ?, appDatabase: AppDatabase ?) {
    val coroutineScope = rememberCoroutineScope()
    val (loggedUser, setLoggedUser) = rememberPreference(stringPreferencesKey("LoggedUser"), "")
    val (loggedUserPictureId, setLoggedUserPictureId) = rememberPreference(intPreferencesKey("LoggedUserPictureId"), R.drawable.blank_profile)
    val (loggedUserFirstName, setLoggedUserFirstName) = rememberPreference(stringPreferencesKey("LoggedUserFirstName"), "")
    val (loggedUserLastName, setLoggedUserLastName) = rememberPreference(stringPreferencesKey("LoggedUserLastName"), "")

    val (email, setEmail) = remember { mutableStateOf("") }
    val (pictureId, _) = remember { mutableStateOf(R.drawable.blank_profile) }
    val (firstName, setFirstName) = remember { mutableStateOf("") }
    val (lastName, setLastName) = remember { mutableStateOf("") }

    val (password, setPassword) = remember { mutableStateOf("") }
    val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }
    val (confirmPassword, setConfirmPassword) = remember { mutableStateOf("") }
    val (confirmPasswordVisible, setConfirmPasswordVisible) = remember { mutableStateOf(false) }

    val dataEntryError = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(LittleLemonLightGrey),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            fontFamily = karlaFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = "Personal information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 20.dp),
            color = Color(0xFF000000),
        )
        Image(
            painter = painterResource(
                id = if(loggedUser != "") loggedUserPictureId else pictureId),
            contentDescription = "profile",
            contentScale = ContentScale.Fit,

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(150.dp)
                .width(150.dp)
        )
        Text(
            text = "Change image",
            style = TextStyle(
                fontSize = 15.sp
            ),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()

        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = if(loggedUser != "") loggedUserFirstName else firstName,
            onValueChange = {
                setFirstName(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 18.sp
            ),
            placeholder = {
                Text(
                    text = "First Name",
                    fontFamily = karlaFamily,
                    fontSize = 18.sp,
                    color = Color.Gray)
            },
            readOnly = loggedUser != "",
            colors = TextFieldDefaults.textFieldColors(
                textColor = LittleLemonCharcoal,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            )
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = if(loggedUser != "") loggedUserLastName else lastName,
            onValueChange = {
                setLastName(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = "Last Name",
                    fontFamily = karlaFamily,
                    fontSize = 20.sp,
                    color = Color.Gray)
            },
            readOnly = loggedUser != "",
            colors = TextFieldDefaults.textFieldColors(
                textColor = LittleLemonCharcoal,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent)
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = if(loggedUser != "") loggedUser else email,
            onValueChange = {
                setEmail(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = "Email",
                    fontFamily = karlaFamily,
                    fontSize = 20.sp,
                    color = Color.Gray)
            },
            readOnly = loggedUser != "",
            colors = TextFieldDefaults.textFieldColors(
                textColor = LittleLemonCharcoal,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent            )
        )
        when(loggedUser){
             "" -> {
                 TextField(
                     modifier = Modifier
                         .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
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
                         textColor = LittleLemonCharcoal,
                         disabledTextColor = Color.Transparent,
                         focusedIndicatorColor = Color.Transparent,
                         unfocusedIndicatorColor = Color.Transparent,
                         disabledIndicatorColor = Color.Transparent,
                         containerColor = Color.Transparent)
                 )
                 TextField(
                     modifier = Modifier
                         .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                         .fillMaxWidth()
                         .border(
                             border = BorderStroke(1.dp, LittleLemonGreen),
                             shape = RoundedCornerShape(8.dp)
                         ),
                     value = confirmPassword,
                     onValueChange = {
                         setConfirmPassword(it)
                     },
                     textStyle = TextStyle(
                         fontFamily = karlaFamily,
                         fontSize = 20.sp,
                     ),
                     placeholder = {
                         Text(
                             text="Confirm Password",
                             fontFamily = karlaFamily,
                             fontSize = 20.sp,
                             color = Color.Gray)
                     },
                     visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                     keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                     trailingIcon = {
                         val image = if (confirmPasswordVisible)
                             Icons.Filled.Visibility
                         else Icons.Filled.VisibilityOff
                         val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                         IconButton(onClick = {setConfirmPasswordVisible(!confirmPasswordVisible)}){
                             Icon(imageVector  = image, description)
                         }
                     },
                     colors = TextFieldDefaults.textFieldColors(
                         textColor = LittleLemonCharcoal,
                         disabledTextColor = Color.Transparent,
                         focusedIndicatorColor = Color.Transparent,
                         unfocusedIndicatorColor = Color.Transparent,
                         disabledIndicatorColor = Color.Transparent,
                         containerColor = Color.Transparent
                     )
                 )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                if(loggedUser != "") {
                    setLoggedUser("")
                    setLoggedUserPictureId(R.drawable.blank_profile)
                    setLoggedUserFirstName("")
                    setLoggedUserLastName("")

                    navController!!.navigate(Login.route){
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
                else {
                    dataEntryError.value = checkAccountInformation(
                        AccountRoom(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            password = password,
                            profilePictureId = R.drawable.blank_profile),
                        confirmPassword
                    )
                    if(dataEntryError.value == "") {
                        val newPictureId = MainActivity.getRandomProfilePictureId()
                        setLoggedUser(email)
                        setLoggedUserPictureId(newPictureId)
                        setLoggedUserFirstName(firstName)
                        setLoggedUserLastName(lastName)

                        coroutineScope.launch(Dispatchers.IO) {
                            appDatabase!!.AccountDao().insertAll(
                                AccountRoom(
                                    email = email,
                                    profilePictureId = newPictureId,
                                    firstName = firstName,
                                    lastName = lastName,
                                    password = password
                                )
                            )
                        }
                        navController!!.navigate(Home.route){
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = LittleLemonYellow,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.DarkGray
            ),
            shape = RoundedCornerShape( 8.dp)
        ) {
            Text(
                text= if(loggedUser != "") "Log Out" else "Create Account"
            )
        }
        when(dataEntryError.value) {
            "missingInformation" -> {
                AlertDialog(
                    onDismissRequest = {},
                    title = {Text("Missing Information!")},
                    text = {Text("You need to provide information in all the fields.")},
                    confirmButton = {
                        Button(onClick={
                            dataEntryError.value = ""
                        }){
                            Text("OK")
                        }
                    }
                )
            }
            "passwordMismatch" -> {
                AlertDialog(
                    onDismissRequest = {},
                    title = {Text("Password Mismatch!")},
                    text = {Text("The passwords don't match. Please enter and confirm the password again.")},
                    confirmButton = {
                        Button(onClick={
                            dataEntryError.value = ""
                        }){
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

fun checkAccountInformation(account: AccountRoom, confirmPassword: String): String {
    if(account.firstName == "" || account.lastName == "" || account.email == "" || account.password == "" ) {
        return "missingInformation"
    }
    else if(account.password != confirmPassword) {
        return "passwordMismatch"
    }
    return ""
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    LittleLemonTheme {
        ProfileScreen(null, null)
    }
}
