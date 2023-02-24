package pathfinding.ui

import androidx.compose.runtime.*

class ViewModel(){


    var mode by mutableStateOf(Mode.Draw)
    var selectedCells by mutableStateOf(listOf<Pair<Int, Int>>())

    fun changeMode(new:Mode){
        mode = new
    }

    fun clearSelected(){
        selectedCells = emptyList()
    }

}