package model.algorithms

import model.graph.Node
import model.graph.State
import ui.ViewModel


fun dfs(viewModel: ViewModel) {
    val start = viewModel.startNode ?: return
    val end = viewModel.endNode ?: return
    val stack = mutableListOf<Node>()
    stack.add(start)
    start.parent = null
    viewModel.updateNodeState(start, State.VISITED)

    while (stack.isNotEmpty()) {
        delay()
        val currentNode = stack.removeAt(stack.lastIndex)
        if (currentNode == end) { // end node found, backtrack the path
            backtrackPath(end, viewModel)
            return
        }

        searchNeighbors(currentNode, viewModel){ neighbor: Node ->
            neighbor.parent = currentNode.position
            viewModel.updateNodeState(neighbor, State.VISITED)
            stack.add(neighbor)
        }
    }
}
