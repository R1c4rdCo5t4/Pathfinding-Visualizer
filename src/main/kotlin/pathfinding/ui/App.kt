package pathfinding.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier


@Composable
@Preview
fun App(title: String) {

    val viewModel by remember { mutableStateOf(ViewModel()) }

    Column{
        Title(title.uppercase())
        Buttons(viewModel)
        Grid(lines = 18, columns = 30, cellSize = 24.dp, viewModel)
        Credits()
    }

}


@Composable
fun Title(title: String){
    Row(
        Modifier
            .fillMaxWidth()
            .absolutePadding(0.dp,20.dp,0.dp,0.dp)
        ,

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
fun Credits(){
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){ Text("<by R1c4rdCo5t4/>",
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Thin
        )
    }
}


enum class Mode {
    Draw, Eraser
}
