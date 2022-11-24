package pathfinding.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*



@Composable
fun Grid(lines:Int, columns:Int, cellSize: Dp){

    Box(
        Modifier
            .background(Color.White)
            .padding(10.dp)
            .clickable(onClick = {
                // TOOD
                // CHECK COORDINATES
            })

    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            repeat(lines) { line ->
                Row(
                    Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    repeat(columns) { col ->
                        Cell(line,col,Modifier
                            .background(Color.Gray)
                            .size(cellSize)
                            .border(2.dp, Color.White, RectangleShape)
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(line:Int, col: Int, modifier:Modifier, onClick: () -> Unit) {
    var color by remember { mutableStateOf(Color.Gray) }
    Box(modifier = modifier.background(color).clickable(onClick = { color = Color.Red }))
}
/*
 val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(modifier = modifier
        .background(color),
        onClick = { color = Color.Red },
    interactionSource=interactionSource) {
        println(isPressed)
    }
*/