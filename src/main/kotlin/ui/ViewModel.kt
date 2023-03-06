package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.algorithms.Algorithm
import model.algorithms.bfs
import model.algorithms.dfs
import model.graph.*
import model.graph.State



class ViewModel {

    val rows: Int = 18
    val columns: Int = 30
    val cellSize: Dp = 24.dp
    var mode by mutableStateOf(State.ORIGIN)
    var grid by mutableStateOf((0 until rows).map { row -> (0 until columns).map { col -> Node(Position(col, row)) } })
    var startNode: Node? by mutableStateOf(null)
    var endNode: Node? by mutableStateOf(null)
    var algorithm = Algorithm.BFS
    var running = false
    private var th : Thread? = null


    init {
        grid.forEach { row ->
            row.forEach { node ->
                node.setNeighbors(grid)
            }
        }
    }

    fun clearAll() {
        grid = grid.map { row -> row.map { node -> node.copy(state = State.UNVISITED) } }
        stop()
    }

    fun clearPath() {
        grid = grid.map { row ->
            row.map { node ->
                if (node.state == State.VISITED || node.state == State.PATH) node.copy(state = State.UNVISITED) else node
            }
        }
        stop()
    }

    fun updateNodeState(node: Node, newState: State){
        updateNodeState(node.position.x, node.position.y, newState)
    }

    fun updateNodeState(x:Int, y: Int, newState: State) {

        val position = Position(x, y)
        val updatedGrid = grid.map { r ->
            r.map { n ->
                if (n.position == position) {
                    if ((n.state == State.ORIGIN || n.state == State.DESTINATION) && newState != State.UNVISITED) {
                        return
                    }
                    val node = n.copy(state = newState)

                    val startPos = startNode?.position
                    val endPos = endNode?.position
                    if (newState == State.ORIGIN && startPos != position) {
                        if (startPos != null) grid[startPos.y][startPos.x].state = State.UNVISITED
                        startNode = node
                    }
                    else if (newState == State.DESTINATION && endPos != position) {
                        if (endPos != null) grid[endPos.y][endPos.x].state = State.UNVISITED
                        endNode = node
                    }
                    node
                } else n
            }
        }
        grid = updatedGrid.toList()
    }


    fun run() {
        clearPath()
        running = true
        th = Thread {
            try {
                when (algorithm) {
                    Algorithm.BFS -> bfs(this)
                    Algorithm.DFS -> dfs(this)
                    else -> return@Thread
                }
            }
            catch(e: InterruptedException){ }
            running = false
        }
        th?.start()
    }

    fun stop(){
        th?.interrupt()
        running = false
    }
}