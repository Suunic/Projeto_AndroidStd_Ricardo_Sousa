package com.example.projetoandroidstdricardosousa

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
var Winner: Boolean=false
var player1Diff: Int = 0
var player2Diff: Int = 0


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
            Screen1(navigateBack = { navController.popBackStack()
                player1Diff = 0
                player2Diff = 0
                Winner = false
            })
        }
        composable("screen2") {
            Screen2(navigateBack = { navController.popBackStack()
                player1Diff = 0
                player2Diff = 0
                Winner = false})
        }
        composable("screen3") {
            Screen3(navigateBack = { navController.popBackStack()
                player1Diff = 0
                player2Diff = 0
                Winner = false})
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
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = navigateToScreen1) {
                Text("1 Player")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = navigateToScreen2) {
                Text("2 Players")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = navigateToScreen3) {
                Text("Options")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = exitApp) {
                Text("Exit")
            }
        }
    } else {
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
}

@Composable
fun Screen1(navigateBack: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.height(40.dp)) {
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


                    DropdownMenuItem(
                        text = { Text("D6") },
                        onClick = { Option = 6; Log.d("TAG", "Option changed to $Option") })
                    DropdownMenuItem(
                        text = { Text("D20") },
                        onClick = { Option = 20; Log.d("TAG", "Option changed to $Option") })

                }
            }

            Spacer(modifier = Modifier.padding(end = 16.dp))
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
        when (Option) {

            6 -> {
                DiceSix(1, Value)
            }

            20 -> {
                DiceTwenty(1, Value)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            when(Winner){
                false -> {
                    Text("Player 1: " + player1.score)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Player 2(Bot): " + player2.score)

                }
                true -> {
                    if(player1Diff < player2Diff)Text("Player 1 Wins")
                    if(player1Diff >player2Diff)Text("Player 2 Wins")
                    if(player1Diff == player2Diff)Text("Draw")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = navigateBack) {
                Text("Back to Main Screen")
                player1.score = 0
                player2.score = 0

            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Dice Column
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                when (Option) {
                    6 -> {
                        DiceSix(1, Value)
                    }

                    20 -> {
                        DiceTwenty(1, Value)
                    }
                }
            }

            // Button and Score Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                when(Winner){
                    false -> {
                        Text("Player 1: " + player1.score)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Player 2(Bot): " + player2.score)

                    }
                    true -> {
                        if(player1Diff < player2Diff)Text("Player 1 Wins")
                        if(player1Diff > player2Diff)Text("Player 2 Wins")
                        if(player1Diff == player2Diff)Text("Draw")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = navigateBack) {
                    Text("Back to Main Screen")
                    player1.score = 0
                    player2.score = 0
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            // Options Button
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.height(40.dp)) {
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
                        DropdownMenuItem(
                            text = { Text("D6") },
                            onClick = { Option = 6; Log.d("TAG", "Option changed to $Option") })
                        DropdownMenuItem(
                            text = { Text("D20") },
                            onClick = { Option = 20; Log.d("TAG", "Option changed to $Option") })
                    }
                }
            }
        }

    }
}
@Composable
fun Screen2(navigateBack: () -> Unit) {var expanded by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.height(40.dp)) {
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


                    DropdownMenuItem(
                        text = { Text("D6") },
                        onClick = { Option = 6; Log.d("TAG", "Option changed to $Option") })
                    DropdownMenuItem(
                        text = { Text("D20") },
                        onClick = { Option = 20; Log.d("TAG", "Option changed to $Option") })

                }
            }

            Spacer(modifier = Modifier.padding(end = 16.dp))
        }
        Spacer(modifier = Modifier.padding(end = 16.dp))
        when (Option) {

            6 -> {
                DiceSix(2, Value)
            }

            20 -> {
                DiceTwenty(2, Value)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            when(Winner){
                false -> {
                    Text("Player 1: " + player1.score)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Player 2: " + player2.score)

                }
                true -> {
                    if(player1Diff < player2Diff)Text("Player 1 Wins")
                    if(player1Diff > player2Diff)Text("Player 2 Wins")
                    if(player1Diff == player2Diff)Text("Draw")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = navigateBack) {
                Text("Back to Main Screen")
                player1.score = 0
                player2.score = 0

            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Dice Column
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                when (Option) {
                    6 -> {
                        DiceSix(2, Value)
                    }

                    20 -> {
                        DiceTwenty(2, Value)
                    }
                }
            }

            // Button and Score Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                when(Winner){
                    false -> {
                        Text("Player 1: " + player1.score)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Player 2: " + player2.score)

                    }
                    true -> {
                        if(player1Diff < player2Diff)Text("Player 1 Wins")
                        if(player1Diff > player2Diff)Text("Player 2 Wins")
                        if(player1Diff == player2Diff)Text("Draw")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = navigateBack) {
                    Text("Back to Main Screen")
                    player1.score = 0
                    player2.score = 0
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            // Options Button
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.height(40.dp)) {
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
                        DropdownMenuItem(
                            text = { Text("D6") },
                            onClick = { Option = 6; Log.d("TAG", "Option changed to $Option") })
                        DropdownMenuItem(
                            text = { Text("D20") },
                            onClick = { Option = 20; Log.d("TAG", "Option changed to $Option") })
                    }
                }
            }
        }

    }
}

@Composable
fun Screen3(navigateBack: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var customValue by rememberSaveable { mutableStateOf(Value) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            Button(onClick = { expanded = true }) {

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(IntrinsicSize.Max)
                ) {

                    DropdownMenuItem(text = { Text("D6" ) }, onClick = { Option=6; Log.d("TAG", "Option changed to $Option") })
                    DropdownMenuItem(text = { Text("D20") }, onClick = { Option=20; Log.d("TAG", "Option changed to $Option") })

                }
                Text("D$Option")
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
    var checkValue by rememberSaveable { mutableStateOf(false) }
    var result by remember { mutableStateOf(1) }
    var textRoll by remember { mutableStateOf("Player 1") }
    var currentPlayer by remember { mutableStateOf(player1) }
    val configuration = LocalConfiguration.current



    val imageResource = when (result) {

        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(imageResource), contentDescription = result.toString()
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
                            when(currentPlayer){

                                player1 -> {
                                    textRoll = if (currentPlayer == player1) "Player 2" else "Player 1"
                                    getDiceValue(player = currentPlayer, roll = result)
                                    currentPlayer = player2
                                }
                                player2 -> {
                                    textRoll = if (currentPlayer == player2) "Player 1" else "Player 2"
                                    getDiceValue(player = currentPlayer, roll = result)
                                    currentPlayer = player1
                                }
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
                    Winner=checkValue
                    player1Diff = Math.abs(value - player1.score)
                    player2Diff = Math.abs(value - player2.score)
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
    } else{
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = imageResource), // Ensure imageResource is correctly defined
                    contentDescription = result.toString()
                )
            }
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        if (!checkValue) {
                            result = (1..6).random()
                            if (playerSelect == 1) {
                                textRoll = "Player 1"
                                getDiceValue(player = player1, roll = result)
                                result = (1..6).random()
                                getDiceValue(player = player2, roll = result)


                            } else {
                                when(currentPlayer){

                                    player1 -> {
                                        textRoll = if (currentPlayer == player1) "Player 2" else "Player 1"
                                        getDiceValue(player = currentPlayer, roll = result)
                                        currentPlayer = player2
                                    }
                                    player2 -> {
                                        textRoll = if (currentPlayer == player2) "Player 1" else "Player 2"
                                        getDiceValue(player = currentPlayer, roll = result)
                                        currentPlayer = player1
                                    }
                                }

                            }
                        }
                    }
                ) {
                    Text("Roll($textRoll)", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        checkValue = true
                        Winner=checkValue
                        player1Diff = Math.abs(value - player1.score)
                        player2Diff = Math.abs(value - player2.score)
                        when {
                            player1Diff < player2Diff -> Log.d("TAG", "Player 1 Wins")
                            player1Diff == player2Diff -> Log.d("TAG", "Draw")
                            else -> Log.d("TAG", "Player 2 Wins")
                        }
                    }
                ) {
                    Text("Stop", fontSize = 24.sp)
                }
            }
        }


    }
}


@Composable
fun DiceTwenty( playerSelect: Int, value: Int) {
    var checkValue by rememberSaveable { mutableStateOf(false) }
    var result by remember { mutableStateOf(1) }
    var textRoll by remember { mutableStateOf("Player 1") }
    var currentPlayer by remember { mutableStateOf(player1) }
    val configuration = LocalConfiguration.current

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
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
                            when(currentPlayer){

                                player1 -> {
                                    textRoll = if (currentPlayer == player1) "Player 2" else "Player 1"
                                    getDiceValue(player = currentPlayer, roll = result)
                                    currentPlayer = player2
                                }
                                player2 -> {
                                    textRoll = if (currentPlayer == player2) "Player 1" else "Player 2"
                                    getDiceValue(player = currentPlayer, roll = result)
                                    currentPlayer = player1
                                }
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
                    Winner=checkValue
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
    }else{
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = imageResource), // Ensure imageResource is correctly defined
                    contentDescription = result.toString()
                )
            }
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
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
                                when(currentPlayer){

                                    player1 -> {
                                        textRoll = if (currentPlayer == player1) "Player 2" else "Player 1"
                                        getDiceValue(player = currentPlayer, roll = result)
                                        currentPlayer = player2
                                    }
                                    player2 -> {
                                        textRoll = if (currentPlayer == player2) "Player 1" else "Player 2"
                                        getDiceValue(player = currentPlayer, roll = result)
                                        currentPlayer = player1
                                    }
                                }

                            }
                        }
                    }
                ) {
                    Text("Roll($textRoll)", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        checkValue = true
                        Winner=checkValue
                        player1Diff = Math.abs(value - player1.score)
                        player2Diff = Math.abs(value - player2.score)
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
    var currentValue by rememberSaveable { mutableStateOf(value) }

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

