@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.projetoandroidstdricardosousa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projetoandroidstdricardosousa.ui.theme.ProjectGameTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectGameTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navigateToScreen1 = { navController.navigate("screen1") },
                navigateToScreen2 = { navController.navigate("screen2") },
                exitApp = { (it as ComponentActivity).finishAffinity() }
            )
        }
        composable("screen1") {
            Screen1(navigateBack = { navController.popBackStack() })
        }
        composable("screen2") {
            Screen2(navigateBack = { navController.popBackStack() })
        }


    }
}

@Composable
fun MainScreen(navigateToScreen1: () -> Unit, navigateToScreen2: () -> Unit, exitApp:() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = navigateToScreen1) {
            Text("1 Player")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateToScreen2) {
            Text("2 Players")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = exitApp) {
            Text("Exit")
        }
    }
}

@Composable
fun Screen1(navigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("This is Screen 1")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Back to Main Screen")
        }
    }
}

@Composable
fun Screen2(navigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("This is Screen 2")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Back to Main Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ProjectGameTheme {
        MainScreen(navigateToScreen1 = {}, navigateToScreen2 = {}, exitApp = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen1() {
    ProjectGameTheme {
        Screen1(navigateBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen2() {
    ProjectGameTheme {
        Screen2(navigateBack = {})
    }
}

