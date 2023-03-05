package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.graph.*
import model.graph.State


class ViewModel{

    val rows: Int = 18
    val columns: Int = 30
    val cellSize: Dp = 24.dp
    var mode by mutableStateOf(Mode.Draw)
    var grid by mutableStateOf((0 until rows).map{ row -> (0 until columns).map { col -> Node(Position(col, row)) } })

    fun changeMode(new:Mode){
        mode = new
    }

    fun clearSelected(){
        grid = grid.map { row -> row.map{ node -> Node(node.position) }}
    }

    fun updateNodeState(col:Int, row:Int, newState: State) {
        val position = Position(col, row)
        val updatedGrid = grid.map { r ->
            r.map { node ->
                if (node.position == position) Node(position, node.parent, newState) else node
            }
        }
        grid = updatedGrid
    }
}