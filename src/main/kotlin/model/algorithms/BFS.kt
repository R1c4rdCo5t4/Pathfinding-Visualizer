package model.algorithms

import model.graph.Node
import model.graph.State
import ui.ViewModel
import java.util.ArrayDeque


fun bfs(viewModel: ViewModel) {
    val start = viewModel.startNode ?: return
    val end = viewModel.endNode ?: return
    val queue = ArrayDeque<Node>()
    queue.add(start)
    start.parent = null
    viewModel.updateNodeState(start, State.VISITED)

    while (queue.isNotEmpty()) {
        delay()
        val currentNode = queue.removeFirst()
        if (currentNode == end) { // end node found, backtrack the path
            backtrackPath(currentNode, viewModel)
            return
        }

        searchNeighbors(currentNode, viewModel){ neighbor: Node ->
            neighbor.parent = currentNode.position
            viewModel.updateNodeState(neighbor, State.VISITED)
            queue.add(neighbor)
        }
    }
}



