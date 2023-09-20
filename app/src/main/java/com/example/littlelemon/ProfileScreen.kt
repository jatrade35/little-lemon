package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.NavHostController
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
    val (firstName, setFirstName) = remember { mutableStateOf("") }
    val (lastName, setLastName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (confirmPassword, setPassword2) = remember { mutableStateOf("") }
    val dataEntryError = remember { mutableStateOf("") }
    val loggedIn: Boolean by rememberPreference(booleanPreferencesKey("LoggedIn"), true)

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
                .padding(start = 10.dp, top = 40.dp, end = 10.dp, bottom = 50.dp),
            color = Color(0xFF000000),
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 30.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = firstName,
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
                    color = Color.LightGray)
            },
            readOnly = loggedIn,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.DarkGray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent            )
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 30.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, LittleLemonGreen),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = lastName,
            onValueChange = {
                setLastName(it)
            },
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 18.sp
            ),
            placeholder = {
                Text(
                    text = "Last Name",
                    fontFamily = karlaFamily,
                    fontSize = 18.sp,
                    color = Color.LightGray)
            },
            readOnly = loggedIn,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.DarkGray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent)
        )
        TextField(
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 30.dp)
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
                fontSize = 18.sp
            ),
            placeholder = {
                Text(
                    text = "Email",
                    fontFamily = karlaFamily,
                    fontSize = 18.sp,
                    color = Color.LightGray)
            },
            readOnly = loggedIn,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.DarkGray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent            )
        )
        when{
            !loggedIn -> {
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 30.dp)
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
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = "Password",
                            fontFamily = karlaFamily,
                            fontSize = 18.sp,
                            color = Color.LightGray)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.DarkGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent            )
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 30.dp)
                        .fillMaxWidth()
                        .border(
                            border = BorderStroke(1.dp, LittleLemonGreen),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    value = confirmPassword,
                    onValueChange = {
                        setPassword2(it)
                    },
                    textStyle = TextStyle(
                        fontFamily = karlaFamily,
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            fontFamily = karlaFamily,
                            fontSize = 18.sp,
                            color = Color.LightGray)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.DarkGray,
                        disabledTextColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent            )
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                if(loggedIn) {
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
                            password = password),
                        confirmPassword
                    )
                    if(dataEntryError.value == "") {
                        coroutineScope.launch(Dispatchers.IO) {
                            appDatabase!!.AccountDao().insertAll(
                                AccountRoom(
                                    email = email,
                                    firstName = firstName,
                                    lastName = lastName,
                                    password = password
                                )
                            )
                        }
                    }

                    navController!!.navigate(Home.route){
                        popUpTo(0) {
                            inclusive = true
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
                text= if(loggedIn) "Log Out" else "Create Account"
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