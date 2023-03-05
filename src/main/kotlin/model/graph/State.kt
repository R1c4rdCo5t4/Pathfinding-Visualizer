package model.graph

enum class State {
    ORIGIN, DESTINATION, OBSTACLE, UNVISITED, VISITED, PATH;

    private val elements get() = values().slice(0..3)

    fun next() : State {
        val nextIdx = elements.indexOf(this) + 1
        val modeIdx = if (nextIdx >= elements.size) 0 else nextIdx
        return elements[modeIdx]
    }
}