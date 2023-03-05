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
    var mode by mutableStateOf(State.VISITED)
    var grid by mutableStateOf((0 until rows).map{ row -> (0 until columns).map { col -> Node(Position(col, row)) } })
    private var startNode : Node? by mutableStateOf(null)
    private var endNode : Node? by mutableStateOf(null)


    init{
        grid.forEach { row ->
            row.forEach{ node ->
                node.setNeighbors(grid)
            }
        }
    }

    fun changeMode(newState: State){
        mode = newState
    }

    fun clearSelected(){
        grid = grid.map { row -> row.map{ node -> Node(node.position) }}
    }

    private fun reset(){
        grid = grid.map { row -> row.map { node ->
            if (node.state == State.VISITED || node.state == State.PATH) node.copy(state = State.UNVISITED) else node }
        }
    }

    fun updateNodeState(col:Int, row:Int, newState: State) {

        val position = Position(col, row)
        val updatedGrid = grid.map { r ->
            r.map { n ->
                if (n.position == position) {
                    if ((n.state == State.ORIGIN || n.state == State.DESTINATION) && newState != State.UNVISITED){
                        return
                    }
                    val node = n.copy(state=newState)

                    val startPos = startNode?.position
                    val endPos = endNode?.position
                    val pos = Position(col, row)
                    if(newState == State.ORIGIN && startPos != pos){
                        if(startPos != null) grid[startPos.y][startPos.x].state = State.UNVISITED
                        startNode = node
                    }
                    else if(newState == State.DESTINATION && endPos != pos){
                        if(endPos != null) grid[endPos.y][endPos.x].state = State.UNVISITED
                        endNode = node
                    }
                    node
                }
                else n
            }
        }

        grid = updatedGrid.toList()
    }

    fun bfs(){
        val start = startNode ?: return
        val end = endNode ?: return
        println("bfs")
        reset()
        model.algorithms.bfs(start, end, this)
    }
}