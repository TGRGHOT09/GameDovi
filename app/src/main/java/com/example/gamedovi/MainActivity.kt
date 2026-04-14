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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
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
            Text(text = "Âm thanh", modifier = Modifier.weight(1f))
            Checkbox(
                checked = isSoundEnabled,
                onCheckedChange = { viewModel.setSoundEnabled(it) }
            )
            Text(text = "Bật")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Điểm cao nhất", modifier = Modifier.weight(1f))
            Text(text = highScore.toString())
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tự động lưu game", modifier = Modifier.weight(1f))
            Checkbox(
                checked = isAutoSaveEnabled,
                onCheckedChange = { viewModel.setAutoSaveEnabled(it) }
            )
            Text(text = "Bật")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Volume")
        Slider(
            value = volume,
            onValueChange = { viewModel.setVolume(it) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
