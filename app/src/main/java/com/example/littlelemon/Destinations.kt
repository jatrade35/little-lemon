package com.example.littlelemon

interface Destinations {
    val route: String
}

object Login : Destinations {
    override val route = "Login"
}

object PasswordRecovery : Destinations {
    override val route = "PasswordRecovery"
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}
