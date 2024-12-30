package com.example.jetpackcompose.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ForecastData(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: ForecastSys,
    val dt_txt: String,
    val rain: Rain? = null
)

data class ForecastSys(val pod: String)
data class Rain(val `3h`: Double? = null)

// Composable function to display the weather forecast
@Composable
fun ForecastWeatherView(forecastData: List<ForecastItem>) {
    LazyColumn {
        items(forecastData) { item ->
            ForecastWeatherCard(forecastItem = item)
        }
    }
}

// Composable function to display the details of a single forecast item
@Composable
fun ForecastWeatherCard(forecastItem: ForecastItem) {
    val date = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault()).format(Date(forecastItem.dt * 1000))

    Card(modifier = Modifier.padding(8.dp)) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = date, fontWeight = FontWeight.Bold)
            Text(text = "Temperature: ${forecastItem.main.temp}°C")
            Text(text = "Weather: ${forecastItem.weather.firstOrNull()?.description.orEmpty()}")
            Text(text = "Rain: ${forecastItem.rain?.`3h`} mm (if any)")
            Text(text = "Wind Speed: ${forecastItem.wind.speed} m/s")
        }
    }
}
