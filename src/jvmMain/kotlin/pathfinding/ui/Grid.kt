package pathfinding.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Grid(lines:Int, columns:Int, cellSize: Dp){

    Box(
        Modifier
            .offset(y = 100.dp)
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            repeat(columns) { line ->
                Row(
                    Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    repeat(lines) { col ->
                        Cell(Modifier.background(Color.Gray).size(cellSize).border(2.dp, Color.White, RectangleShape)){

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.clickable(onClick = onClick))
}
