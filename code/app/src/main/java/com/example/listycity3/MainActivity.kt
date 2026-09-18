package com.example.listycity3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.listycity3.ui.theme.ListyCity3Theme
/*
* Notes for future reference:
* The term "it" can be used in lambda expressions if the compiler can understand the value without parameters
* Works for cases where you have a function with 1 parameter
* Information based on Kotlin Programming Language at https://kotlinlang.org/docs/lambdas.html#returning-a-value-from-a-lambda-expression
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cityRepository = CityRepository()
        setContent {
            ListyCity3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        onAddCity = { cityRepository.addCity(it) },
                        onUpdateCity = { selectedCity, updatedCity -> cityRepository.updateCity(selectedCity, updatedCity) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
