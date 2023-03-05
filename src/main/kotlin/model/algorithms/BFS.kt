package model.algorithms

import model.graph.Node
import model.graph.State
import ui.ViewModel

fun bfs(
    startNode: Node,
    endNode: Node,
    viewModel: ViewModel
) {
    val th = Thread {
        val queue = ArrayDeque<Node>()
        queue.add(startNode)
        startNode.parent = null
        viewModel.updateNodeState(startNode.position.x, startNode.position.y, State.VISITED)

        while (queue.isNotEmpty()) {
            Thread.sleep(5)
            val currentNode = queue.removeFirst()

            if (currentNode == endNode) { // end node found, backtrack the path
                var node: Node? = endNode
                while (node != null) {
                    Thread.sleep(5)
                    if (node.state == State.ORIGIN) break
                    val parentPos = node.parent ?: break
                    viewModel.updateNodeState(node.position.x, node.position.y, State.PATH)
                    node = viewModel.grid[parentPos.y][parentPos.x]
                }
                return@Thread
            }

            // add neighbors of the current node to the queue
            for (neighborPos in currentNode.neighbors) {
                val neighbor = viewModel.grid[neighborPos.y][neighborPos.x]
                if (neighbor.state != State.OBSTACLE && neighbor.state != State.VISITED) {
                    neighbor.parent = currentNode.position
                    viewModel.updateNodeState(neighbor.position.x, neighbor.position.y, State.VISITED)
                    queue.add(neighbor)

                }
            }
        }
    }
    th.start()
}




