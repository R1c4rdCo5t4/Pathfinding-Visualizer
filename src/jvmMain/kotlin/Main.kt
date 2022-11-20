import pathfinding.ui.App
import androidx.compose.material.*
import androidx.compose.ui.window.*


fun main() = application(exitProcessOnExit = false) {
    val title = "Pathfinding Visualizer"
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = title
    ) {
        MaterialTheme {
            App(title)
        }
    }
}
