package pathfinding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun Buttons(){
    val buttons by remember { mutableStateOf(mutableListOf("START", "STOP", "CLEAR PATH", "CLEAR ALL")) }

    Row(
        Modifier
            .fillMaxWidth()
            .offset(0.dp, 50.dp)
            .background(Color.White),

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        DropdownButton(listOf("Dijkstra", "A*", "DFS", "BFS"))
        buttons.forEach {
            Column {
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
                    Text(it, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                }

            }
        }


    }
}


@Composable
fun DropdownButton(items: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    Button(onClick = { expanded = !expanded }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
        Text(items[selectedIndex], fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s)
                }
            }
        }
    }
}