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
    NavHost(navController = navController, startDestination = "home" ){
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
            LoginScreen()
        }
        composable(route = "register"){
            RegistroScreen()

        }
        composable(route = "profileAccount"){
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                ProfileAccountScreen(navController)
                var selectedNavItem by remember { mutableStateOf(NavItem.PROFILE) }
                NavMenu(
                    selectedNavItem = selectedNavItem,
                    onNavItemClicked = { selectedNavItem = it },
                    navController = navController
                )
            }
        }
        composable(route = "editProfileAccount"){
            EditProfileScreen(navController)
        }
    }
}