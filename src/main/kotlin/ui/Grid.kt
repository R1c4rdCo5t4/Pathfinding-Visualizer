package ui

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.input.pointer.pointerInput
import model.graph.Node
import model.graph.State


@Composable
fun Grid(viewModel: ViewModel) {

    val padding = 10.dp
    Box(

        Modifier
            .background(Color.White)
            .pointerInput(Unit) {

                detectDragGestures { change, dragAmount ->
                    val touchPos = change.position
                    val row = ((touchPos.y - padding.toPx() / 4) / viewModel.cellSize.toPx()).toInt()
                    val col = ((touchPos.x - padding.toPx() * 3) / viewModel.cellSize.toPx()).toInt()

                    if(row !in 0 until viewModel.rows || col !in 0 until viewModel.columns){
                        return@detectDragGestures
                    }
                    when(viewModel.mode){
                        Mode.Draw -> {
                            viewModel.updateNodeState(col, row, State.OBSTACLE)
                        }
                        Mode.Eraser -> {
                            viewModel.updateNodeState(col, row, State.UNVISITED)
                        }
                    }
                }
            }
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            repeat(viewModel.rows) { row ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(viewModel.columns) { col ->
                        val node = viewModel.grid[row][col]
                        Cell(viewModel.cellSize, node)
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(size: Dp,  node: Node) {

    Box(modifier = Modifier
        .background(node.color)
        .size(size)
        .border(2.dp, Color.White, RectangleShape)) {

    }
}
