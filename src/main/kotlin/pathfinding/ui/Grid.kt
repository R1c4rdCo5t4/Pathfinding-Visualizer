package pathfinding.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.selects.select



@Composable
fun Grid(lines: Int, columns: Int, cellSize: Dp, viewModel: ViewModel) {

    val padding = 10.dp
    Box(

        Modifier
            .background(Color.White)
            .pointerInput(Unit) {

                detectDragGestures { change, dragAmount ->
                    val touchPos = change.position

                    val row = ((touchPos.y - padding.toPx()) / cellSize.toPx()).toInt()
                    val col = ((touchPos.x - padding.toPx() * 2) / cellSize.toPx()).toInt()
                    val cell = col to row


                    when(viewModel.mode){
                        Mode.Draw -> {
                            if (cell !in viewModel.selectedCells){
                                viewModel.selectedCells += cell
                            }
                        }

                        Mode.Eraser -> {
                            if (cell in viewModel.selectedCells) {
                                viewModel.selectedCells -= cell
                            }
                        }
                    }
                }
            }
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            repeat(lines) { line ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(columns) { col ->
                        val cellColor = if ((col to line) in viewModel.selectedCells) Color.Black else Color.Gray
                        Cell(
                            col,
                            line,
                            Modifier
                                .background(cellColor)
                                .size(cellSize)
                                .border(2.dp, Color.White, RectangleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(line: Int, col: Int, modifier: Modifier) {
    Box(modifier = modifier) {
        // Do nothing
    }
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