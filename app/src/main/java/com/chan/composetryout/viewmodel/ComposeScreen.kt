package com.chan.composetryout.viewmodel

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
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
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
fun ComposeScreen() {
    Scaffold {
        val navController = rememberNavController()
        NavigationComponent(navController)
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navController.navigate("detail") }) {
            Text(text = "Go to detail")
        }
        Button(onClick = { navController.navigate("detailWithViewModel") }) {
            Text(text = "Go to detail with ViewModel")
        }
    }
}

@Composable
fun DetailScreen() {
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