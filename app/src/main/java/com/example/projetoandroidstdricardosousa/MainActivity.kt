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


var Option: Int = 6
var Value:  Int = 21


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
                navigateToScreen3 = { navController.navigate("screen3") },
                exitApp = { exitApplication() }
            )
        }
        composable("screen1") {
            Screen1(navigateBack = { navController.popBackStack() })
        }
        composable("screen2") {
            Screen2(navigateBack = { navController.popBackStack() })
        }
        composable("screen3") {
            Screen3(navigateBack = { navController.popBackStack() })
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
    navigateToScreen3: () -> Unit,


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
        Button(onClick = navigateToScreen3) {
            Text("Options")
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

                DropdownMenuItem(text = { Text("D4" ) }, onClick = { Option=4; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D6" ) }, onClick = { Option=6; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D20") }, onClick = { Option=20; Log.d("TAG", "Option changed to $Option") })

            }
        }

        Spacer(modifier = Modifier.padding(end = 16.dp))
    }
    Spacer(modifier = Modifier.padding(end = 16.dp))
    when (Option) {
        4 -> {
            // diceFour(1, player1)
        }
        6 -> {
            DiceSix(1, Value)
        }
        20 -> {
            DiceTwenty(2, Value )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text("Player 1: " + player1.score)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Player 2(Bot): " + player2.score)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Back to Main Screen")

        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun Screen2(navigateBack: () -> Unit) {
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

                DropdownMenuItem(text = { Text("D4" ) }, onClick = { Option=4; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D6" ) }, onClick = { Option=6; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D20") }, onClick = { Option=20; Log.d("TAG", "Option changed to $Option") })

            }
        }

        Spacer(modifier = Modifier.padding(end = 16.dp))
    }
    Spacer(modifier = Modifier.padding(end = 16.dp))
    when (Option) {
        4 -> {
            // diceFour(1, player1)
        }
        6 -> {
            DiceSix(2, Value)
        }
        20 -> {
            DiceTwenty(2, Value )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text("Player 1: " + player1.score)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Player 2(Bot): " + player2.score)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Back to Main Screen")
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun Screen3(navigateBack: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var customValue by remember { mutableStateOf(Value) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            Button(onClick = { expanded = true }) {
                Text("D$Option ")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                DropdownMenuItem(text = { Text("D4" ) }, onClick = { Option=4; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D6" ) }, onClick = { Option=6; Log.d("TAG", "Option changed to $Option") })
                DropdownMenuItem(text = { Text("D20") }, onClick = { Option=20; Log.d("TAG", "Option changed to $Option") })

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select the desired value to play with", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        NumberPicker(
            value = customValue,
            onValueChange = { customValue = it },
            range = 1..100
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
                navigateBack()
                Value = customValue
            }) {
            Text("Back to Main Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ProjectGameTheme {
        MainScreen(navigateToScreen1 = {}, navigateToScreen2 = {}, navigateToScreen3 = {}, exitApp = {})
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
fun DiceSix( playerSelect: Int, value: Int) {
    var checkValue by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(1) }
    var textRoll by remember { mutableStateOf("Player 1") }
    var currentPlayer by remember { mutableStateOf(player1) }

    val imageResource = when (result) {

        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Spacer(modifier = Modifier.height(20.dp))
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

                if(!checkValue){

                    result = (1..6).random()
                    if (playerSelect == 1) {
                        textRoll = "Player 1"
                        getDiceValue(player = player1, roll = result)
                        result = (1..6).random()
                        getDiceValue(player = player2, roll = result)


                    } else {
                        textRoll = if (currentPlayer == player1) "Player 1" else "Player 2"
                        getDiceValue(player = currentPlayer, roll = result)
                        if (currentPlayer == player1) {
                            currentPlayer = player2

                        } else {
                            currentPlayer = player1

                        }
                    }
                }
            },

        ) {
            Text("Roll($textRoll)", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                checkValue = true
                val player1Diff = Math.abs(value - player1.score)
                val player2Diff = Math.abs(value - player2.score)
                when {
                    player1Diff < player2Diff -> Log.d("TAG", "Player 1 Wins")
                    player1Diff == player2Diff -> Log.d("TAG", "Draw")
                    else -> Log.d("TAG", "Player 2 Wins")
            }
            }

        ){
            Text(text = "Stop", fontSize = 24.sp)
        }
    }
}

@Composable
fun DiceTwenty( playerSelect: Int, value: Int) {
    var checkValue by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(1) }
    var textRoll by remember { mutableStateOf("Player 1") }
    var currentPlayer by remember { mutableStateOf(player1) }

    val imageResource = when (result) {

        1 -> R.drawable.d20_1
        2 -> R.drawable.d20_2
        3 -> R.drawable.d20_3
        4 -> R.drawable.d20_4
        5 -> R.drawable.d20_5
        6 -> R.drawable.d20_6
        7 -> R.drawable.d20_7
        8 -> R.drawable.d20_8
        9 -> R.drawable.d20_9
        10 -> R.drawable.d20_10
        11 -> R.drawable.d20_11
        12 -> R.drawable.d20_12
        13 -> R.drawable.d20_13
        14 -> R.drawable.d20_14
        15 -> R.drawable.d20_15
        16 -> R.drawable.d20_16
        17 -> R.drawable.d20_17
        18 -> R.drawable.d20_18
        19 -> R.drawable.d20_19
        else -> R.drawable.d20_20

    }
    Spacer(modifier = Modifier.height(20.dp))
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

                if(!checkValue){

                    result = (1..20).random()
                    if (playerSelect == 1) {
                        textRoll = "Player 1"
                        getDiceValue(player = player1, roll = result)
                        result = (1..20).random()
                        getDiceValue(player = player2, roll = result)


                    } else {
                        textRoll = if (currentPlayer == player1) "Player 1" else "Player 2"
                        getDiceValue(player = currentPlayer, roll = result)
                        if (currentPlayer == player1) {
                            currentPlayer = player2

                        } else {
                            currentPlayer = player1

                        }
                    }
                }
            },

            ) {
            Text("Roll($textRoll)", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                checkValue = true
                val player1Diff = Math.abs(value - player1.score)
                val player2Diff = Math.abs(value - player2.score)
                when {
                    player1Diff < player2Diff -> Log.d("TAG", "Player 1 Wins")
                    player1Diff == player2Diff -> Log.d("TAG", "Draw")
                    else -> Log.d("TAG", "Player 2 Wins")
                }
            }

        ){
            Text(text = "Stop", fontSize = 24.sp)
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

@Composable
fun NumberPicker(value: Int, onValueChange: (Int) -> Unit, range: IntRange) {
    var currentValue by remember { mutableStateOf(value) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { if (currentValue > range.first) currentValue--; onValueChange(currentValue) }) {
            Text("-")
        }

        Text(currentValue.toString(), fontSize = 24.sp)

        Button(onClick = { if (currentValue < range.last) currentValue++; onValueChange(currentValue) }) {
            Text("+")
        }
    }
}

