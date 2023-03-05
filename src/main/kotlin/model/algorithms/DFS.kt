package model.algorithms

import model.graph.Node
import model.graph.State
import ui.ViewModel

fun dfs(
    startNode: Node,
    endNode: Node,
    viewModel: ViewModel
) {
    val th = Thread {
        val stack = mutableListOf<Node>()
        stack.add(startNode)
        startNode.parent = null
        viewModel.updateNodeState(startNode.position.x, startNode.position.y, State.VISITED)

        while (stack.isNotEmpty()) {
            if(!viewModel.running) return@Thread
            Thread.sleep(10)
            val currentNode = stack.removeAt(stack.lastIndex)

            if (currentNode == endNode) { // end node found, backtrack the path
                var node: Node? = endNode
                while (node != null) {
                    if(!viewModel.running) return@Thread
                    Thread.sleep(10)
                    if (node.state == State.ORIGIN) break
                    val parentPos = node.parent ?: break
                    viewModel.updateNodeState(node.position.x, node.position.y, State.PATH)
                    node = viewModel.grid[parentPos.y][parentPos.x]
                }
                return@Thread
            }

            // add neighbors of the current node to the stack
            for (neighborPos in currentNode.neighbors.reversed()) {
                if(!viewModel.running) return@Thread
                val neighbor = viewModel.grid[neighborPos.y][neighborPos.x]
                if (neighbor.state != State.OBSTACLE && neighbor.state != State.VISITED) {
                    neighbor.parent = currentNode.position
                    viewModel.updateNodeState(neighbor.position.x, neighbor.position.y, State.VISITED)
                    stack.add(neighbor)
                }
            }
        }
    }
    th.start()
}
