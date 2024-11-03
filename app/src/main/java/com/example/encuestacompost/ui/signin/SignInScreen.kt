package com.example.encuestacompost.ui.signin

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.encuestacompost.R
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: SigInViewModel,
    navigateToMain: () -> Unit,
    navigateBack: () -> Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val context = LocalContext.current
        SignIn(
            modifier = Modifier.align(Alignment.Center),
            viewModel = viewModel,
            navigateToMain = navigateToMain,
            navigateBack = navigateBack,
            context
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        viewModel = SigInViewModel(LocalContext.current),
        navigateToMain = TODO(),
        navigateBack = TODO()
    )
}

@Composable
fun SignIn(
    modifier: Modifier,
    viewModel: SigInViewModel,
    navigateToMain: () -> Unit,
    navigateBack: () -> Boolean,
    context: Context
) {
    val username: String by viewModel.username.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val signInEnabled: Boolean by viewModel.signInEnabled.observeAsState(initial = false)

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            UsernameField(
                username = username,
                onUsernameChanged = { viewModel.onSigInChanged(it, password) })
            PasswordField(
                password = password,
                onPasswordChanged = { viewModel.onSigInChanged(username, it) })
            SigInBotton(signInEnabled) {
                coroutineScope.launch {
                    viewModel.onSigInSelected(navigateToMain, navigateBack, context)
                }
            }
            LoaderLottieSurvey()// Your Lottie animation composable
        }
    }
}

@Composable
fun SigInBotton(signInEnabled: Boolean, onSigInSelected: () -> Unit) {
    Button(
        onClick = { onSigInSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        enabled = signInEnabled
    ) {
        Text(text = "Sign In")
    }
}

@Composable
fun PasswordField(password: String, onPasswordChanged: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        /*label = { Text(text = "Contraseña") },*/
        placeholder = { Text(text = "Ingrese contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun UsernameField(username: String, onUsernameChanged: (String) -> Unit) {
    TextField(
        value = username,
        onValueChange = { onUsernameChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        /*label = { Text(text = "Nombre de usuario") },*/
        placeholder = { Text(text = "Ingrese CUE") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun LoaderLottieSurvey() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.marcando_survey)
    )
    LottieAnimation(composition)
}

