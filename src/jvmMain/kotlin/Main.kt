import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*



fun main() = application(exitProcessOnExit = false) {
    val title = "Pathfinding Visualizer"
    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = title
    ) {
        MaterialTheme(darkColors()) {
            App(title)
        }
    }
}


@Composable
@Preview
fun App(title: String) {

    Title(title.uppercase())
    Buttons()
    Grid(lines = 30, columns = 18, cellSize = 24.dp)
    Credits()

}


@Composable
fun Title(title: String){
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            title,
            fontSize = 40.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Thin

        )
    }
}

@Composable
fun Buttons(){
    val buttons by remember { mutableStateOf(mutableListOf("START", "STOP", "CLEAR PATH", "CLEAR ALL")) }

    Row(
        Modifier
            .fillMaxWidth()
            .offset(0.dp, 50.dp)
            .background(Color.White),

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        DropdownButton()
        buttons.forEach {
            Column {
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
                    Text(it, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                }

            }
        }


    }
}


@Composable
fun DropdownButton() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Dijkstra", "A*", "DFS", "BFS")
    var selectedIndex by remember { mutableStateOf(0) }
    Button(onClick = { expanded = !expanded }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)) {
        Text(items[selectedIndex], fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = s)
                }
            }
        }
    }
}

@Composable
fun Grid(lines:Int, columns:Int, cellSize: Dp){

    Box(
        Modifier
            .offset(y = 100.dp)
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            repeat(columns) { line ->
                Row(Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    repeat(lines) { col ->
                        Cell(Modifier.background(Color.Gray).size(cellSize).border(2.dp, Color.White, RectangleShape)){

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Cell(modifier:Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.clickable(onClick = onClick))
}

@Composable
fun Credits(){
    Row(
        Modifier
            .fillMaxWidth()
            .offset(y=545.dp),
        horizontalArrangement = Arrangement.Center
    ){ Text("<by R1c4rdCo5t4/>",
        fontSize = 10.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Thin
    )}
}



