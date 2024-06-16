package com.example.projetoandroidstdricardosousa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projetoandroidstdricardosousa.ui.theme.ProjectGameTheme
import kotlin.system.exitProcess


var Option: Int = 2

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
                exitApp = { exitApplication() }
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

fun exitApplication() {
    exitProcess(0)
}

@Composable
fun MainScreen(
    navigateToScreen1: () -> Unit,
    navigateToScreen2: () -> Unit,
    exitApp: () -> Unit
) {
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
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.height(40.dp)){
            Button(onClick = { expanded = true }) {
                Text("Options")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .width(IntrinsicSize.Max)
            ) {

                DropdownMenuItem(text = { Text("D4") }, onClick = { Option=1; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D6") }, onClick = { Option=2; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D20") }, onClick = { Option=3; Log.d("TAG", "Option changed to $Option") })

            }
        }

        Spacer(modifier = Modifier.padding(end = 16.dp))
    }
    Spacer(modifier = Modifier.padding(end = 16.dp))
    when (Option) {
        1 -> {
            // diceFour()
        }
        2 -> {
            DiceSix()
        }
        3 -> {
            // diceTwenty()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text("Valor: " + player1.score)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Back to Main Screen")
        }
        Spacer(modifier = Modifier.height(40.dp))
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

@Composable
fun DiceSix(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )

        Button(
            onClick = {
                result = (1..6).random()
                getDiceValue(player = player1, roll = result)
            },
        ) {
            Text(text = "Roll", fontSize = 24.sp)
        }
    }
}

class Player {
    var score by mutableStateOf(0)
}

val player1 = Player()
val player2 = Player()

fun getDiceValue(player: Player, roll: Int) {
    player.score += roll
}

