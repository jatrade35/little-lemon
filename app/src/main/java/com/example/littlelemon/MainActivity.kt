package com.example.littlelemon

import android.content.Context
import android.os.Bundle
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                AppScreen(this)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScreen(context: Context) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val loggedIn: Boolean by rememberPreference(booleanPreferencesKey("LoggedIn"), true)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile picture",
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                    NavigationDrawerItem(
                        icon = {},
                        label = { Text("View Profile") },
                        selected = true,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(Profile.route)}
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        icon = {},
                        label = { Text("Logout") },
                        selected = true,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(Login.route)}
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

            }
        }
    ) {
        Scaffold(
            topBar = {
                Header()
            },
            floatingActionButton = {
                when {
                    loggedIn -> Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
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
                MyNavigation(context, navController = navController)
            }
        }
    }
}

@Composable
fun MyNavigation(context: Context, navController: NavHostController) {
    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
    val accounts by database.AccountDao().getAll().observeAsState(emptyList())
    val startDestination = if (accounts.isEmpty()) Onboarding.route else Login.route
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