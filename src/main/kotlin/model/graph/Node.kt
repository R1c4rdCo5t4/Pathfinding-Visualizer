package model.graph

import androidx.compose.ui.graphics.Color

data class Position(val x: Int, val y: Int)

enum class State {
    ORIGIN, DESTINATION, OBSTACLE, UNVISITED, VISITED, PATH;

    private val editables get() = values().slice(0..3)

    fun next() : State {
        val nextIdx = editables.indexOf(this) + 1
        val modeIdx = if (nextIdx >= editables.size) 0 else nextIdx
        return editables[modeIdx]
    }
}

data class Node(
    val position: Position,
    var parent: Position? = null,
    var state: State = State.UNVISITED,
    val neighbors: MutableList<Position> = mutableListOf()

) {
    val color get() = when(state){
        State.ORIGIN -> Color.Red
        State.DESTINATION -> Color.Blue
        State.OBSTACLE -> Color.Black
        State.VISITED -> Color.Cyan
        State.UNVISITED -> Color.Gray
        State.PATH -> Color.Green
    }


    fun setNeighbors(grid: List<List<Node>>) {
        for (dx in -1..1) {
            for (dy in -1..1) {
                if (dx == 0 && dy == 0) continue
                val x = position.x + dx
                val y = position.y + dy
                if (x in grid[0].indices && y in grid.indices) {
                    val neighbor = grid[y][x]
                    neighbors.add(neighbor.position)
                }
            }
        }
    }
}
