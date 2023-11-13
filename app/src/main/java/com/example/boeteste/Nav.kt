package com.example.boeteste

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boeteste.components.navMenu.NavItem
import com.example.boeteste.components.navMenu.NavMenu
import com.example.boeteste.pages.HomeScreen
import com.example.boeteste.pages.editProfile.EditProfileScreen
import com.example.boeteste.pages.login.LoginScreen
import com.example.boeteste.pages.profileAccount.ProfileAccountScreen
import com.example.boeteste.pages.registro.RegistroScreen

@Composable
fun Nav(){
    val navController = rememberNavController()

    var isAuthenticated by remember { mutableStateOf(false) }
    NavHost(navController = navController, startDestination = if (isAuthenticated) "home" else "login" ){
        composable(route = "home"){
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ){

                HomeScreen()
                var selectedNavItem by remember { mutableStateOf(NavItem.HOME) }
                NavMenu(
                    selectedNavItem = selectedNavItem,
                    onNavItemClicked = { selectedNavItem = it },
                    navController = navController
                )
            }
        }
        composable(route = "login"){
            LoginScreen(
                navController = navController,
                onLoginSuccess = { isAuthenticated = true }
            )
        }
        composable(route = "register"){
            RegistroScreen(navController = navController)
        }
        composable(route = "profileAccount"){
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                ProfileAccountScreen(
                    navController = navController,
                    onLogout = { isAuthenticated = false }
                )
                var selectedNavItem by remember { mutableStateOf(NavItem.PROFILE) }
                NavMenu(
                    selectedNavItem = selectedNavItem,
                    onNavItemClicked = { selectedNavItem = it },
                    navController = navController
                )
            }
        }
        composable(route = "editProfileAccount"){
            EditProfileScreen(
                navController = navController,
                onLogout = {isAuthenticated = false}
            )
        }
    }
}