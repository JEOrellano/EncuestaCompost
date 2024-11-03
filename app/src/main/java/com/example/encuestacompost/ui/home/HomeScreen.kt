package com.example.encuestacompost.ui.home

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Box(Modifier.fillMaxSize()) {
        Home(Modifier.align(Alignment.Center), viewModel,LocalContext.current)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(HomeViewModel(LocalContext.current))
}

@Composable
fun Home(modifier: Modifier, viewModel: HomeViewModel, context: Context) {
    val contEncuestas: String by viewModel.contEncuestas.observeAsState(initial = "0")
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            ContEncuestasField(contEncuestas)
            HomeContarEncuestas() {
                coroutineScope.launch {
                    viewModel.onHomeContarencuestas(context)
                }
            }
        }
    }
}

@Composable
fun HomeContarEncuestas(onHomeContarencuestas: () -> Unit) {
    //TODO("Not yet implemented")
    //Contar encuestas
    onHomeContarencuestas()
}

@Composable
fun ContEncuestasField(contEncuestas: String) {
    Text(
        text = "ENCUESTAS REALIZADAS: ${contEncuestas}/10",
        modifier = Modifier.wrapContentWidth()
    )
}
