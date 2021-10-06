package com.chan.composetryout.viewmodel

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * Created by chandra-1765$ on 24/09/21$.
 */

@Composable
fun ComposeScreen() {
    Scaffold {
        val navController = rememberNavController()
        NavigationComponent(navController)
    }
}

val screens = arrayListOf<String>("home", "detail", "detailWithViewModel")
@Composable
fun NavigationComponent(navController: NavHostController) {
    Log.d("ChanLog", "NavigationComponent: ")
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        /*screens.forEach { screenId ->
            composable(screenId) {
                NavigateScreen(navController, screenId)
            }
        }*/
        composable("home") {
            HomeScreen(navController)
        }
        composable("detail") {
            DetailScreen()
        }
        composable("detailWithViewModel") {
            DetailScreen(viewModel())
        }
    }
}

@Composable
fun NavigateScreen(navController: NavHostController, screenId: String) {
    Log.d("ChanLog", "NavigateScreen: ")
    when(screenId) {
        "home" -> HomeScreen(navController)
        "detail" -> DetailScreen()
        "detailWithViewModel" -> DetailScreen(viewModel())
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Log.d("ChanLog", "HomeScreen: ")
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            navController.navigate("detail") {
                restoreState = true
            }
        }) {
            Text(text = "Go to detail")
        }
        Button(onClick = {
            navController.navigate("detailWithViewModel")
        }) {
            Text(text = "Go to detail with ViewModel")
        }
    }
}

@Composable
fun DetailScreen() {
    Log.d("ChanLog", "DetailScreen: ")
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail")
    }
}


@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = viewModel.getDetailText())
    }
}

class DetailViewModel : ViewModel() {

    fun getDetailText(): String {
        // some imaginary backend call
        return "Detail With ViewModel"
    }
}