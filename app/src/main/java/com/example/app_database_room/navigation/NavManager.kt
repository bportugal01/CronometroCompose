package com.example.app_database_room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_database_room.viewModels.CronometroViewModel
import com.example.app_database_room.viewModels.CronosViewModel
import com.example.app_database_room.views.AdicionarView
import com.example.app_database_room.views.EditarView
import com.example.app_database_room.views.HomeView

@Composable
fun NavManager(cronometroVM: CronometroViewModel, cronosVM: CronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, cronosVM)
        }

        composable("AddView"){
            AdicionarView(navController, cronometroVM, cronosVM)
        }

        composable("EditView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditarView(navController, cronometroVM, cronosVM, id)
        }
    }
}