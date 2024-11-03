package com.example.encuestacompost.ui.synchronize

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SynchronizeScreen(viewModel: SynchronizeViewModel) {
    Box(Modifier.fillMaxSize()) {
        Synchronize(
            Modifier.align(Alignment.Center),
            viewModel,
            LocalContext.current
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SynchronizeScreenPreview() {
    SynchronizeScreen(SynchronizeViewModel(LocalContext.current))
}

@Composable
fun Synchronize(
    modifier: Modifier,
    viewModel: SynchronizeViewModel,
    current: Context
) {
    val textoPrueba: String by viewModel.textoPrueba.observeAsState(initial = "")

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            TextoPruebaField(textoPrueba)
            SynchronizeBotton() {
                coroutineScope.launch {
                    viewModel.onSynchronizeSelected(current)
                }
            }
        }
    }
}

@Composable
fun SynchronizeBotton(onSynchronizeSelected: () -> Unit) {
    Button(
        onClick = { onSynchronizeSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "SINCRONIZAR")
    }
}

@Composable
fun TextoPruebaField(textoPrueba: String) {
    Text(
        text = textoPrueba,
        modifier = Modifier.fillMaxWidth()
    )

}
