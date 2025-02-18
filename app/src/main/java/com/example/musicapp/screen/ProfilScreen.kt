@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.musicapp.screen

import com.example.musicapp.data.Song
import com.example.musicapp.screen.Search.SongItem
import de.syntax_institut.syntaxfitness.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.musicapp.data.songListe

import kotlinx.serialization.Serializable


@Serializable
object Profil {

    @Composable
    fun ProfilScreen(username: String, onSongClick: (Song) -> Unit) {
        var favoriteSong by remember { mutableStateOf<Song?>(null) }
        var followering by remember { mutableStateOf(0) }
        var following by remember { mutableStateOf(0) }
        var playlist by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profilbild
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Profilbild",
                modifier = Modifier
                    .size(350.dp)
                    .clip(RoundedCornerShape(size = 150.dp))

            )


            // Follower, Following und Playlist Informationen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat(count = following, label = "Follower")
                ProfileStat(count = following, label = "Folgt")
                ProfileStat(count = playlist, label = "Playlists")
            }
            Text(
                text = username,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { favoriteSong = songListe.random() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Zufälliger Lieblingssong wählen")
            }

            Spacer(modifier = Modifier.height(24.dp))

            favoriteSong?.let { song ->
                Text(
                    "Dein Lieblingssong:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                SongItem(song = song, onSongClick = onSongClick)
            }
        }
    }

    @Composable
    fun ProfileStat(count: Int, label: String) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}