import ui.App
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*


fun main() = application(exitProcessOnExit = false) {
    val title = "Pathfinding Visualizer"
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = title,
        state = WindowState(
            position= WindowPosition(Alignment.Center),
            size = DpSize(800.dp, 675.dp)
        )
    ) {
        MaterialTheme {
            App(title)
        }
    }
}
