package com.example.encuestacompost.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.encuestacompost.R
import com.example.encuestacompost.entidad.Encuestador
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navigateToSignIn: () -> Unit, navigateBack: () -> Boolean) {
    LaunchedEffect(key1 = true) {
        delay(5000) // Simulate a delay of 5 seconds
        navigateBack()
        navigateToSignIn()
    }
    Splash(viewModel = SplashViewModel(LocalContext.current))
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash(SplashViewModel(LocalContext.current))
}

@Composable
fun Splash(viewModel: SplashViewModel) {
    viewModel.viewModelScope.launch {
        viewModel.altaEncuestador(Encuestador("001", "1234"))
        viewModel.altaEncuestador(Encuestador("002", "1234"))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) { // Fondo transparente para el Box
        Image(
            painter = painterResource(id = R.drawable.back_spl),
            contentDescription = "Imagen Splash",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la escala de la imagen según tus necesidades
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LoaderLottieSurvey()// Your Lottie animation composable
                Spacer(modifier = Modifier.height(16.dp)) // Add some spacing if needed
                Text(
                    text = "Desarrollado por Grupo 1 TUSI FRGP",
                    //modifier = Modifier.align(Alignment.Center), // Centra el texto en el Box
                    color = Color.Black, // Cambia el color del texto si es necesario
                    fontSize = 20.sp,// Ajusta el tamaño del texto según tus necesidades
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(16.dp)) // Add some spacing if needed
                Text(
                    text = "Version 1.0.0",
                    modifier = Modifier.padding(vertical = 16.dp),
                    //modifier = Modifier.align(Alignment.Center), // Centra el texto en el Box
                    color = Color.Black, // Cambia el color del texto si es necesario
                    fontSize = 30.sp,// Ajusta el tamaño del texto según tus necesidades
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Composable
fun LoaderLottieSurvey() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.marcando_survey)
    )
    LottieAnimation(composition)
}