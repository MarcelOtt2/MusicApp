package com.example.musicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musicapp.data.Home.HomeScreen
import com.example.musicapp.data.songListe
import com.example.musicapp.screen.Play.PlayScreen
import com.example.musicapp.screen.Profil.ProfilScreen
import com.example.musicapp.screen.Search.SearchScreen
import com.example.musicapp.ui.theme.MusicAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(bottomBar = {
                    NavigationBar {
                        NavigationBarItem(icon = {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        },
                            label = { Text("Home") },
                            selected = currentDestination?.hierarchy?.any { it.route == "home" } == true,
                            onClick = {
                                navController.navigate("home") {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                        NavigationBarItem(icon = {
                            Icon(
                                Icons.Default.Search, contentDescription = "Search"
                            )
                        },
                            label = { Text("Search") },
                            selected = currentDestination?.hierarchy?.any { it.route == "search" } == true,
                            onClick = {
                                navController.navigate("search") {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                        NavigationBarItem(icon = {
                            Icon(
                                Icons.Default.Person, contentDescription = "Profil"
                            )
                        },
                            label = { Text("Profil") },
                            selected = currentDestination?.hierarchy?.any { it.route == "profil" } == true,
                            onClick = {
                                navController.navigate("profil") {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                        NavigationBarItem(icon = {
                            Icon(
                                Icons.Default.PlayArrow, contentDescription = "Play"
                            )
                        },
                            label = { Text("Play") },
                            selected = currentDestination?.hierarchy?.any { it.route == "play" } == true,
                            onClick = {
                                navController.navigate("play") {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(songListe, onSongClick = { song ->
                                navController.navigate("playScreen/${song.id}")
                            })
                        }


                        composable(
                            route = "playScreen/{songId}",
                            arguments = listOf(navArgument("songId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val songId =
                                backStackEntry.arguments?.getInt("songId") ?: return@composable
                            val song = songListe.find { it.id == songId } ?: return@composable
                            PlayScreen(song = song, navigateBack = { navController.popBackStack() })
                        }

                        composable("search") { SearchScreen(  songListe = songListe,
                            onSongClick = { song ->
                                navController.navigate("playScreen/${song.id}")
                            }
                        )
                        }
                        composable("profil") {
                            ProfilScreen(username = "YourUsername", onSongClick = { song ->
                                navController.navigate("playScreen/${song.id}")
                            }
                            )
                        }
                        composable("play") {
                            PlayScreen(song = songListe[0],
                                navigateBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
