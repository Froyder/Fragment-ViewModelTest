package com.example.fragmentviewmodeltest.viewmodel

import com.example.fragmentviewmodeltest.model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

