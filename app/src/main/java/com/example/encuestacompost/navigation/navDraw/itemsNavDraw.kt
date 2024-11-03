package com.example.encuestacompost.navigation.navDraw

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class itemsNavDraw (
    val icon: ImageVector,
    val label: String,
    val badge: String
    ) {
        HOME(
            icon = Icons.Default.Home,
            label = "Home",
            badge = ""
        ),
        SURVEY(
            icon = Icons.AutoMirrored.Filled.List,
            label = "Survey",
            badge = ""
        ),
        SYNCHRONIZE(
            icon = Icons.AutoMirrored.Filled.Send,
            label = "Synchronize",
            badge = ""
        ),
        SIGNOUT(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            label = "SignOut",
            badge = ""
        )
}