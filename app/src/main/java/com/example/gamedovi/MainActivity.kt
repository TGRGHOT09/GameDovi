package com.example.gamedovi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamedovi.ui.theme.GameDoviTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameDoviTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SettingsScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, modifier: Modifier = Modifier) {
    val isSoundEnabled by viewModel.isSoundEnabled.collectAsState()
    val highScore by viewModel.highScore.collectAsState()
    val isAutoSaveEnabled by viewModel.isAutoSaveEnabled.collectAsState()
    val volume by viewModel.volume.collectAsState()

    SettingsContent(
        isSoundEnabled = isSoundEnabled,
        highScore = highScore,
        isAutoSaveEnabled = isAutoSaveEnabled,
        volume = volume,
        onSoundToggle = { viewModel.setSoundEnabled(it) },
        onAutoSaveToggle = { viewModel.setAutoSaveEnabled(it) },
        onVolumeChange = { viewModel.setVolume(it) },
        modifier = modifier
    )
}

@Composable
fun SettingsContent(
    isSoundEnabled: Boolean,
    highScore: Int,
    isAutoSaveEnabled: Boolean,
    volume: Float,
    onSoundToggle: (Boolean) -> Unit,
    onAutoSaveToggle: (Boolean) -> Unit,
    onVolumeChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Cấu hình game đố vui",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
mmi            Text(text = "Âm thanh", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Checkbox(
                checked = isSoundEnabled,
                onCheckedChange = onSoundToggle
            )
            Text(text = "Bật", fontSize = 16.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Điểm cao nhất", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Text(text = highScore.toString(), fontSize = 18.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tự động lưu game", modifier = Modifier.weight(1f), fontSize = 18.sp)
            Checkbox(
                checked = isAutoSaveEnabled,
                onCheckedChange = onAutoSaveToggle
            )
            Text(text = "Bật", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Volume", fontSize = 18.sp)
        Slider(
            value = volume,
            onValueChange = onVolumeChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    GameDoviTheme {
        SettingsContent(
            isSoundEnabled = true,
            highScore = 3500,
            isAutoSaveEnabled = true,
            volume = 0.5f,
            onSoundToggle = {},
            onAutoSaveToggle = {},
            onVolumeChange = {}
        )
    }
}
