package pathfinding.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


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
        )
    }
}