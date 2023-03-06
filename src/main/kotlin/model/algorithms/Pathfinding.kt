package model.algorithms

import model.graph.Node
import model.graph.State
import ui.ViewModel

const val DELAY = 10L

enum class Algorithm { BFS, DFS, Dijkstra, AStar}

fun delay(){
    Thread.sleep(DELAY)
}

// get path backwards from parents
fun backtrackPath(endNode: Node, viewModel: ViewModel) {
    var node: Node? = endNode
    while (node != null) {
        delay()
        if (node.state == State.ORIGIN) break
        val parentPos = node.parent ?: break
        viewModel.updateNodeState(node, State.PATH)
        node = viewModel.grid[parentPos.y][parentPos.x]
    }
}

// add neighbors of the current node to the queue
fun searchNeighbors(currentNode: Node, viewModel: ViewModel, callback: (Node) -> Unit){
    for (neighborPos in currentNode.neighbors) {
        val neighbor = viewModel.grid[neighborPos.y][neighborPos.x]
        if (neighbor.state != State.OBSTACLE && neighbor.state != State.VISITED) {
            callback(neighbor)
        }
    }

}