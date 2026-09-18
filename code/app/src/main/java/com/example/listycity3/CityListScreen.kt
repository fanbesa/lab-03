package com.example.listycity3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun CityListScreen(
    cities: List<City>,
    onAddCity: (City) -> Unit,
    onUpdateCity: (City?, City) -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember { mutableStateOf("") }
    var newProvinceName by remember { mutableStateOf("") }
    var updateCityName by remember { mutableStateOf("") }
    var updateProvinceName by remember { mutableStateOf("") }
    var showAddCityFields by remember { mutableStateOf(false) }
    var showEditCityFields by remember { mutableStateOf(false) }
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var editMessage by remember { mutableStateOf(false) }   // Toggles whether the edit message is displayed

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            /*
             * The Alignment/Arrangement for the Row and all subsequent uses was based on information from
             * Android Developer at https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment
             */
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Spacer(modifier = Modifier.width(8.dp))

            if (editMessage) {
                Text(
                    text = "Select a City to Edit",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                        .padding(all = 8.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields
                    showEditCityFields = false
                    selectedCity = null
                    editMessage = false
                }
            ) {
                Text("+")
            }

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                onClick = {
                    showEditCityFields = !showEditCityFields

                    if (showEditCityFields) {
                        editMessage = true
                    } else { // Clear all Editing Information when exiting Edit Mode
                        selectedCity = null
                        updateCityName = ""
                        updateProvinceName = ""
                        editMessage = false
                    }
                    showAddCityFields = false // Switch between Add Mode and Edit Mode
                }
            ) {
                // The Pencil Emoji was obtained from Emojipedia at https://emojipedia.org/lower-right-pencil
                Text("✎")
            }
        }

        if (showEditCityFields && selectedCity != null) { // Update City Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = updateCityName,
                    onValueChange = { updateCityName = it },
                    label = { Text("Updated City") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = updateProvinceName,
                    onValueChange = { updateProvinceName = it },
                    label = { Text("Updated Province") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        onClick = {
                            updateCityName = ""
                            updateProvinceName = ""
                        }
                    ) {
                        Text("Clear")
                    }

                    Button(
                        modifier = Modifier.padding(horizontal = 12.dp),

                        onClick = {
                            if (updateCityName.isNotBlank() && updateProvinceName.isNotBlank()) {
                                val updatedCity = City(
                                    name = updateCityName,
                                    province = updateProvinceName
                                )

                                onUpdateCity(selectedCity, updatedCity)
                                updateCityName = ""
                                updateProvinceName = ""
                                selectedCity = null
                                showEditCityFields = false
                                editMessage = false
                            }
                        }
                    ) {
                        Text("Update City")
                    }
                }
            }
        }

        if (showAddCityFields) {   // Add City Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = {
                        val newCity = City(name = newCityName, province = newProvinceName)

                        if (newCityName.isNotBlank() && newProvinceName.isNotBlank()) {
                            onAddCity(newCity)
                            newCityName = ""
                            newProvinceName = ""
                            showAddCityFields = false
                        }
                    }
                ) {
                    Text("Add City")
                }

            }
        }

        /*
         * Author: Roman Y https://stackoverflow.com/users/3332499/roman-y
         * Title: "Changing the jetpack compose remember variable from within another function"
         * Answer: https://stackoverflow.com/a/74248468
         * Date: 2022-10-29
         * License: CC-BY-SA 4.0 (International)
         */
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(cities) { index, city ->
                CityRow(
                    city = city,
                    selectedCity = selectedCity,
                    onUpdateCity = { updateCity ->
                        selectedCity = updateCity
                        updateCityName = updateCity.name
                        updateProvinceName = updateCity.province
                        showEditCityFields = true
                        showAddCityFields = false
                    })

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(
    city: City,
    selectedCity: City?,
    onUpdateCity: (City) -> Unit
) {
    var backgroundColor = Color.White

    /* Altering the background color was based on information from Abhishek Pathak
     * at https://medium.com/@myofficework000/rows-columns-and-boxes-in-jetpack-compose-compose-series-02-309e4e1730e5
     */

    /* The method of altering color transparency is from ChatGPT, OpenAI,
     * "In Jetpack Compose, I want to change the background color for a row. How do I make the color more transparent?"
     * 2026-09-17
     */
    if (city == selectedCity) {
        backgroundColor = Color(0xFFBCC9FF).copy(alpha = 0.5F)
    }

    /*
     * Author: Androidew1267 https://stackoverflow.com/users/10891523/androidew1267
     * Title: "How to make Row component whole clickable with padding?"
     * Answer: https://stackoverflow.com/q/71384275
     * Date: 2022-03-07
     * License: CC-BY-SA 4.0 (International)
     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .clickable {
                onUpdateCity(city)
            }
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
            onAddCity = {},

            /* The definition of function types was based on information from
            Kotlin Programming Languages at https://kotlinlang.org/docs/lambdas.html#function-types
            and warning recommendations made by Android Studio
             */
            onUpdateCity = {} as (City?, City) -> Unit
        )
    }
}
