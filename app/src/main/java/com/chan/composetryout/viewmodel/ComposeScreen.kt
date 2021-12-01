package com.chan.composetryout.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val navController = rememberNavController()
    Scaffold {
        NavigationComponent(navController)
    }
}

val screens = arrayListOf<String>("home", "detail", "detailWithViewModel", "list")
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
        Log.d("ChanLog", "NavigateScreen:")
        composable("home") {
            HomeScreen(navController)
        }
        composable("list") {
            ListScreen(navController)
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
    Log.d("ChanLog", "NavigateScreen: $screenId")
    when(screenId) {
        "home" -> HomeScreen(navController)
        "list" -> ListScreen(navController)
        "detail" -> DetailScreen()
        "detailWithViewModel" -> DetailScreen(viewModel())
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Log.d("ChanLog", "HomeScreen: ")
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier.padding(all = 16.dp),
            onClick = {
                //navController.navigate("list")
                /* {
                    restoreState = true
                }*/
                val secondScreenResult = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.getLiveData<String>("your_key")
                //secondScreenResult?.observe(ContextA)
            }) {
            Text(text = "List")
        }
        Button(
            modifier = Modifier.padding(all = 16.dp),
            onClick = {
            navController.navigate("detail")/* {
                restoreState = true
            }*/
        }) {
            Text(text = "Detail")
        }
        Button(
            modifier = Modifier.padding(all = 16.dp),
            onClick = {
            navController.navigate("detailWithViewModel")
        }) {
            Text(text = "Detail with ViewModel")
        }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    Log.d("ChanLog", "ListScreen: Main ")
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(count = 30) { index ->
            Log.d("ChanLog", "ListScreen: Item Compose")
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .background(color = Color.LightGray)
                    .padding(all = 8.dp)
                    .clickable {
                        //navController.navigate("detail")
                        /*{
                               restoreState = true
                           }*/
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("getListResult", Bundle().apply {
                                putString("valueFromList", "ListClicked")
                            })
                    },
                text = "Item-$index",
                color = Color.Red,
                fontSize = 20.sp
            )
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
    Log.d("ChanLog", "DetailScreen: ")
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = viewModel.getDetailText())
    }
}

class DetailViewModel : ViewModel() {

    fun getDetailText(): String {
        // some imaginary backend call
        return "Detail With ViewModel"
    }

    fun prePareData(enum: ChanEnum) {
        when(enum) {
            ChanEnum.First -> {
                enum.someFunction("test")
            }
            ChanEnum.Second -> TODO()
        }
    }
}

enum class ChanEnum(val someProperty: Int, val someFunction: (String) -> String) {
    First(1, { "One" }),
    Second(2, { "Two" })
}