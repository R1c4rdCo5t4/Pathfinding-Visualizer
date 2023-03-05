package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.graph.*
import model.graph.State



class ViewModel {

    val rows: Int = 18
    val columns: Int = 30
    val cellSize: Dp = 24.dp
    var mode by mutableStateOf(State.ORIGIN)
    var grid by mutableStateOf((0 until rows).map { row -> (0 until columns).map { col -> Node(Position(col, row)) } })
    private var startNode: Node? by mutableStateOf(null)
    private var endNode: Node? by mutableStateOf(null)
    var running = false
    val algorithms = listOf("BFS", "DFS", "A*", "Dijkstra")
    var algorithm = algorithms[0]


    init {
        grid.forEach { row ->
            row.forEach { node ->
                node.setNeighbors(grid)
            }
        }
    }

    fun changeMode(newState: State) {
        mode = newState
    }

    fun clearAll() {
        grid = grid.map { row -> row.map { node -> node.copy(state = State.UNVISITED) } }
        running = false
    }

    fun clearPath() {
        grid = grid.map { row ->
            row.map { node ->
                if (node.state == State.VISITED || node.state == State.PATH) node.copy(state = State.UNVISITED) else node
            }
        }
        running = false
    }

    fun updateNodeState(col: Int, row: Int, newState: State) {

        val position = Position(col, row)
        val updatedGrid = grid.map { r ->
            r.map { n ->
                if (n.position == position) {
                    if ((n.state == State.ORIGIN || n.state == State.DESTINATION) && newState != State.UNVISITED) {
                        return
                    }
                    val node = n.copy(state = newState)

                    val startPos = startNode?.position
                    val endPos = endNode?.position
                    val pos = Position(col, row)
                    if (newState == State.ORIGIN && startPos != pos) {
                        if (startPos != null) grid[startPos.y][startPos.x].state = State.UNVISITED
                        startNode = node
                    } else if (newState == State.DESTINATION && endPos != pos) {
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
        val start = startNode ?: return
        val end = endNode ?: return
        clearPath()

        when (algorithm) {
            "BFS" -> model.algorithms.bfs(start, end, this)
            "DFS" -> model.algorithms.dfs(start, end, this)
            else -> return
        }
    }

}