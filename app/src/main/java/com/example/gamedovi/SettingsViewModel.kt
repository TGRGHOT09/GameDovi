package com.example.gamedovi

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("game_settings", Context.MODE_PRIVATE)

    private val _isSoundEnabled = MutableStateFlow(sharedPreferences.getBoolean("sound_enabled", true))
    val isSoundEnabled: StateFlow<Boolean> = _isSoundEnabled.asStateFlow()

    private val _highScore = MutableStateFlow(sharedPreferences.getInt("high_score", 3500))
    val highScore: StateFlow<Int> = _highScore.asStateFlow()

    private val _isAutoSaveEnabled = MutableStateFlow(sharedPreferences.getBoolean("auto_save_enabled", true))
    val isAutoSaveEnabled: StateFlow<Boolean> = _isAutoSaveEnabled.asStateFlow()

    private val _volume = MutableStateFlow(sharedPreferences.getFloat("volume", 0.5f))
    val volume: StateFlow<Float> = _volume.asStateFlow()

    fun setSoundEnabled(enabled: Boolean) {
        _isSoundEnabled.value = enabled
        sharedPreferences.edit().putBoolean("sound_enabled", enabled).apply()
    }

    fun setHighScore(score: Int) {
        _highScore.value = score
        sharedPreferences.edit().putInt("high_score", score).apply()
    }

    fun setAutoSaveEnabled(enabled: Boolean) {
        _isAutoSaveEnabled.value = enabled
        sharedPreferences.edit().putBoolean("auto_save_enabled", enabled).apply()
    }

    fun setVolume(volume: Float) {
        _volume.value = volume
        sharedPreferences.edit().putFloat("volume", volume).apply()
    }
}
