package model.graph

import androidx.compose.ui.graphics.Color

data class Position(val x: Int, val y: Int)

enum class State { START, END, OBSTACLE, VISITED, UNVISITED }

data class Node(
    val position: Position,
    var parent: Node? = null,
    var state: State = State.UNVISITED,
    val neighbors: MutableList<Node> = mutableListOf()
) {
    val color get() = when(state){
        State.START -> Color.Red
        State.END -> Color.Blue
        State.OBSTACLE -> Color.Black
        State.VISITED -> Color.Cyan
        State.UNVISITED -> Color.Gray
    }

    fun addNeighbor(n: Node) {
        neighbors.add(n)
    }

    fun reset(){
        parent = null
        state = State.UNVISITED
        neighbors.clear()
    }
}
