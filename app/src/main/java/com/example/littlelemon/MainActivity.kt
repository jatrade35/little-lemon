package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.karlaFamily
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                AppScreen(this)
            }
        }
    }
    companion object{
        private val profilePictureIds: List<Int> = listOf(
            R.drawable.profile1,
            R.drawable.profile2,
            R.drawable.profile3,
            R.drawable.profile4,
            R.drawable.profile5)

        fun getRandomProfilePictureId(): Int {
            return (
                profilePictureIds[Random.nextInt(0, (profilePictureIds.size - 1))]
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScreen(context: Context ?) {
    val database by lazy {
        Room.databaseBuilder(context!!, AppDatabase::class.java, "database").build()
    }

    val (loggedUser, setLoggedUser) = rememberPreference(stringPreferencesKey("LoggedUser"), "")
    val (loggedUserPictureId, setLoggedUserPictureId) = rememberPreference(intPreferencesKey("LoggedUserPictureId"), R.drawable.blank_profile)
    val (loggedUserFirstName, setLoggedUserFirstName) = rememberPreference(stringPreferencesKey("LoggedUserFirstName"), "")
    val (loggedUserLastName, setLoggedUserLastName) = rememberPreference(stringPreferencesKey("LoggedUserLastName"), "")

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight(),
                ){
                    Image(
                        painter = painterResource(id = loggedUserPictureId),
                        contentDescription = "Profile picture",
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                            .width(300.dp)
                            .height(300.dp)
                    )
                    Text(
                        text = "$loggedUserFirstName $loggedUserLastName",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = karlaFamily,
                            fontWeight = FontWeight.Medium),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(Modifier.height(30.dp))
                    NavigationDrawerItem(
                        icon = {},
                        label = {
                            Text(
                                text = "View Profile",
                                style = TextStyle(
                                    fontFamily = karlaFamily,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(Profile.route)}
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = Color.Transparent,
                            unselectedTextColor = LittleLemonGreen,
                            unselectedContainerColor = Color.Transparent,
                        ),
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        icon = {},
                        label = { Text("Delete Profile",
                            style = TextStyle(
                                fontFamily = karlaFamily,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()

                                deleteAccout(
                                    database,
                                    AccountRoom(
                                        email = loggedUser,
                                        profilePictureId = loggedUserPictureId,
                                        firstName = loggedUserFirstName,
                                        lastName = loggedUserLastName,
                                        password = ""
                                    )
                                )
                                setLoggedUser("")
                                setLoggedUserPictureId(R.drawable.blank_profile)
                                setLoggedUserFirstName("")
                                setLoggedUserLastName("")

                                navController.navigate(Login.route)}
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = Color.Transparent,
                            unselectedTextColor = LittleLemonGreen,
                            unselectedContainerColor = Color.Transparent,
                        ),
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        icon = {},
                        label = { Text(
                            text = "Log Out",
                            style = TextStyle(
                                fontFamily = karlaFamily,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                setLoggedUser("")
                                setLoggedUserPictureId(R.drawable.blank_profile)
                                setLoggedUserFirstName("")
                                setLoggedUserLastName("")

                                navController.navigate(Login.route)}
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedBadgeColor = Color.Transparent,
                            unselectedTextColor = LittleLemonGreen,
                            unselectedContainerColor = Color.Transparent,
                        ),
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

            }
        }
    ) {
        Scaffold(
            topBar = {
                Header(navController)
            },
            floatingActionButton = {
                when(loggedUser) {
                    "" -> {}
                    else -> Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(
                                id = loggedUserPictureId
                            ),
                            contentDescription = "profile",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .padding(start = 30.dp, top = 40.dp)
                                .height(50.dp)
                                .width(50.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )
                    }

                }
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                MyNavigation(navController = navController, database)
            }
        }
    }
}

suspend fun deleteAccout(appDatabase: AppDatabase, account: AccountRoom) {
    appDatabase.AccountDao().deleteAccount(account)
}

@Composable
fun MyNavigation(navController: NavHostController, database: AppDatabase) {
    val accounts by database.AccountDao().getAll().observeAsState(emptyList())
    accounts.forEach { Log.d("ReneDebug", it.toString()) }
    val startDestination = if (accounts.isEmpty()) Login.route else Login.route
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Home.route) {
            HomeScreen(navController)
        }
        composable(PasswordRecovery.route) {
            PasswordRecoveryScreen(navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController, database)
        }
        composable(Login.route) {
            LoginScreen(navController, database)
        }
    }
}

@Preview
@Composable
fun MyAppPreview(){
    AppScreen(null)
}