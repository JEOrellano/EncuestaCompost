package com.example.encuestacompost.navigation.navDraw

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encuestacompost.R
import com.example.encuestacompost.navigation.AppNavigationWrapper
import com.example.encuestacompost.navigation.Home
import com.example.encuestacompost.navigation.Survey
import com.example.encuestacompost.navigation.Synchronize
import com.example.encuestacompost.ui.home.HomeScreen
import com.example.encuestacompost.ui.home.HomeViewModel
import com.example.encuestacompost.ui.survey.SurveyScreen
import com.example.encuestacompost.ui.survey.SurveyViewModel
import com.example.encuestacompost.ui.synchronize.SynchronizeScreen
import com.example.encuestacompost.ui.synchronize.SynchronizeViewModel
import com.example.encuestacompost.ui.theme.EncuestaCompostTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawMain() {
    val navController = rememberNavController()
    /*val viewModelStore = remember { ViewModelStore() }
    navController.setViewModelStore(viewModelStore)*/

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    // personalizar el drawer header
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
        Context.MODE_PRIVATE
    ) ?: return
    val cueSP = sharedPref.getString(
        context.getString(R.string.com_example_encuestacompost_USERNAME_KEY),
        "unknon"
    )
    val cue: String by remember { mutableStateOf(cueSP.toString()) }
    val subtitulo: String by remember { mutableStateOf(cueSP.toString()) }
    // personalizar el appBarr
    var title: String by remember { mutableStateOf(context.getString(R.string.app_name)) }
    // recordar seleccion drawerItem
    var selectedItem by remember { mutableStateOf(itemsNavDraw.HOME) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column(
                        Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                //.wrapContentSize()
                                .size(108.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "CUE: $cue",
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 3.dp)
                        )
                        Text(
                            text = "Bienvenido: $subtitulo",
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 3.dp)
                        )
                    }
                }
                HorizontalDivider()
                itemsNavDraw.entries.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.label) },
                        selected = item == selectedItem,
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        },
                        onClick = {
                            selectedItem = item
                            coroutineScope.launch {
                                drawerState.close()
                                navController.popBackStack()
                                title = item.label
                                when (item) {
                                    itemsNavDraw.HOME -> {
                                        navController.navigate(Home)
                                    }

                                    itemsNavDraw.SURVEY -> {
                                        navController.navigate(Survey)
                                    }

                                    itemsNavDraw.SYNCHRONIZE -> {
                                        navController.navigate(Synchronize)
                                    }

                                    itemsNavDraw.SIGNOUT -> {
                                        Toast.makeText(context, "Logout", Toast.LENGTH_SHORT)
                                            .show()
                                        navController.navigate(AppNavigationWrapper)
                                        val sharedPref = context.getSharedPreferences(
                                            context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
                                            Context.MODE_PRIVATE
                                        ) ?: return@launch
                                        val editor = sharedPref.edit()
                                        editor.clear()
                                        editor.apply()
                                    }
                                }
                            }
                        },
                        badge = {
                            if (item.badge != "") {
                                Badge {
                                    Text(text = item.badge)
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "MenuButton"
                            )
                        }
                    },
                )
            }
        ) {
            NavHost(navController = navController, startDestination = Home) {
                composable<Home> { HomeScreen(viewModel = HomeViewModel(context)) }
                composable<Survey> { SurveyScreen(viewModel = SurveyViewModel(context)) }
                composable<Synchronize> { SynchronizeScreen(viewModel = SynchronizeViewModel(context)) }
                composable<AppNavigationWrapper> { AppNavigationWrapper() }
            }
        }
    }
}

@Preview
@Composable
fun NavDrawPreview() {
    EncuestaCompostTheme {
        NavDrawMain()
    }
}