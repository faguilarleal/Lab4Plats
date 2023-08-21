package com.androidfrancis.lab4

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.androidfrancis.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Main() {
    var text1 by remember {mutableStateOf("")}
    var text2 by remember {mutableStateOf("")}


    val listaDeElementos = remember { mutableStateListOf<String>() }
    val ListItemImagen = remember { mutableStateListOf<String>() }


    Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)
        .fillMaxWidth()
    ){

        OutlinedTextField(modifier = Modifier.padding(10.dp)
            .fillMaxWidth(),
            value = text1,
            onValueChange = { text1 = it},
            label = { Text("Receta")}
        )
        OutlinedTextField(modifier = Modifier.padding(10.dp)
            .fillMaxWidth(),
            value = text2,
            onValueChange = { text2 = it},
            label = { Text("URL")}
        )
        Button(modifier = Modifier.padding(10.dp)
            .fillMaxWidth(),
            onClick = {
                listaDeElementos.add(text1)
                //listaDeElementos.add(MyItem(textComida, textLink)) //pipi queria probar así y no salio con lo de item >:(
                ListItemImagen.add(text2)
                text1=""
                text2=""

            }
        ) {
            Text(text = "Click")

        }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){

            itemsIndexed(listaDeElementos) { index, itemm ->
                val item = ListItemImagen[index]
                Cards(itemm, item)
            }

        }
    }

}

    @OptIn(ExperimentalCoilApi::class)
    @RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Cards(receta: String, imagenURL: String){
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .height(100.dp)
    ){
        Column(
            modifier = Modifier.padding(10.dp)
                .height(100.dp)
            )
        {
            Row( Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 15.dp),
                verticalAlignment = Alignment.CenterVertically){
                AsyncImage(
                    model = imagenURL,
                    contentDescription = null,
                )
                Text(
                    buildAnnotatedString {
                        append("Receta de: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.W900)
                        ) {
                            append(receta)
                        }
                    }
                )
            }
        }
    }
}





