@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.musicapp.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.data.Song
import de.syntax_institut.syntaxfitness.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable


@Serializable
object Play {

    @Composable
    fun PlayScreen(song: Song, navigateBack: () -> Unit) {
        var isPlaying by remember { mutableStateOf(false) }
        var currentPosition by remember { mutableStateOf(0f) }
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(isPlaying) {
            while(isPlaying && currentPosition < song.time) {
                delay(1000) // Warte 1 Sekunde
                currentPosition = minOf(currentPosition + 1, song.time.toFloat())
            }
            if (currentPosition >= song.time) {
                isPlaying = false
                currentPosition = 0f
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Back Button
            IconButton(
                onClick = navigateBack,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Album Cover Image
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Album Cover",
                    modifier = Modifier
                        .size(350.dp)
                        .clip(RoundedCornerShape(100.dp))
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = song.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = song.artist,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(20.dp))

                // Play/Pause Button
                IconButton(
                    onClick = {
                        isPlaying = !isPlaying
                        if (!isPlaying) {
                            coroutineScope.launch {
                                currentPosition = 0f
                            }
                        }
                    },
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(64.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Progress Slider
                Slider(
                    value = currentPosition,
                    onValueChange = {
                        currentPosition = it
                        if (!isPlaying) {
                            isPlaying = true
                        }
                    },
                    valueRange = 0f..song.time.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                // Time Display
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatTime(currentPosition.toInt()),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = formatTime(song.time),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
// Helper function to format time
fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%d:%02d", minutes, remainingSeconds)
}
