package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import model.algorithms.bfs


@Composable
fun Buttons(viewModel: ViewModel){

    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .absolutePadding(0.dp,20.dp,0.dp,20.dp),

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DropdownButton(listOf("Dijkstra", "A*", "DFS", "BFS"))

        CustomButton("START") {
            viewModel.bfs()
        }

        CustomButton(viewModel.mode.name.uppercase()){ viewModel.mode = viewModel.mode.next() }
        CustomButton("CLEAR PATH"){  }
        CustomButton("CLEAR ALL"){ viewModel.clearSelected() }
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit){
    Button(onClick, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
        Text(text, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
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