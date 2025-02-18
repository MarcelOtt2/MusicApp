@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.musicapp.screen

import com.example.musicapp.data.Song
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme

@Serializable
object Search {

    @Composable
    fun SearchScreen(songListe: List<Song>, onSongClick: (Song) -> Unit) {
        var searchQuery by remember { mutableStateOf("") }
        var searchResults by remember { mutableStateOf(listOf<Song>()) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newQuery ->
                    searchQuery = newQuery
                    searchResults = songListe.filter { song ->
                        song.title.contains(newQuery, ignoreCase = true) ||
                                song.artist.contains(newQuery, ignoreCase = true)
                    }
                },
                label = { Text("Suche nach Musik") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Suchsymbol") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(searchResults) { song ->
                    SongItem(song = song, onSongClick = onSongClick)
                    Divider()
                }
            }
        }
    }

    @Composable
    fun SongItem(song: Song, onSongClick: (Song) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSongClick(song) }
                .padding(vertical = 8.dp)
        ) {
            Column {
                Text(text = song.title, style = MaterialTheme.typography.bodyLarge)
                Text(text = song.artist, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
