package com.example.musicapp.navigation



import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.input.key.Key.Companion.Search

import com.example.musicapp.screen.Play
import com.example.musicapp.screen.Profil


data class NavItem<T : Any> (
    val route: T,
    val label: String,
    val icon: ImageVector
)
val route = listOf(
    NavItem(Home,"Home", Icons.Default.Home),
    NavItem(Search, "Search", Icons.Default.Search),
    NavItem(Profil,"Profile", Icons.Default.Person),
    NavItem(Play, "Play", Icons.Default.PlayArrow)
)
