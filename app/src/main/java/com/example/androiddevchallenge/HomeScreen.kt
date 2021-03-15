/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightHomePreview() {
    MyTheme {
        HomeScreen()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkHomePreview() {
    MyTheme(true) {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { HomeBottomNavigation() },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = { HomeFab() }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { Spacer(Modifier.height(56.dp)) }

            item { SearchBar() }

            item { HeadingText("Favorite collections") }
            item { CollectionRow() }

            item { HeadingText("Align your body") }
            item { ExerciseRow(testExersizes) }

            item { HeadingText("Align your mind") }
            item { ExerciseRow(testExersizes.drop(2)) }
        }
    }
}

@Composable
private fun SearchBar() {
    var searchState by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        value = searchState,
        onValueChange = { searchState = it },
        placeholder = { Text("Search") },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface
        ),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
    )
}

@Composable
private fun HomeFab() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 8.dp)
    ) {
        Icon(Icons.Default.PlayArrow, contentDescription = "Play", Modifier.size(24.dp))
    }
}

@Composable
private fun HomeBottomNavigation() {
    Surface(
        elevation = 8.dp
    ) {
        var selectedHome by remember { mutableStateOf(true) }
        BottomNavigation(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .navigationBarsPadding(bottom = true),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
        ) {
            HomeBottomNavItem(
                icon = Icons.Default.Spa,
                text = "Home",
                selected = selectedHome,
                onClick = { selectedHome = true }
            )
            HomeBottomNavItem(
                icon = Icons.Default.AccountCircle,
                text = "Profile",
                selected = !selectedHome,
                onClick = { selectedHome = false }
            )
        }
    }
}

@Composable
private fun RowScope.HomeBottomNavItem(icon: ImageVector, text: String, selected: Boolean, onClick: () -> Unit) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = { Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp)) },
        label = { Text(text.toUpperCase(), style = MaterialTheme.typography.caption) }
    )
}

@Composable
private fun HeadingText(text: String) {
    Text(
        text = text.toUpperCase(),
        style = MaterialTheme.typography.h2,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun CollectionRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {
        items(testCollections) { (item1, item2) ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CollectionItem(item1.second, item1.first)
                CollectionItem(item2.second, item2.first)
            }
        }
    }
}

@Composable
fun ExerciseRow(exercizes: List<Pair<String, String>>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
    ) {
        items(exercizes) {
            ExercizeItem(it.second, it.first)
        }
    }
}

@Composable
fun CollectionItem(url: String, text: String) {
    Card(modifier = Modifier.size(192.dp, 56.dp), shape = MaterialTheme.shapes.small, elevation = 0.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CoilImage(
                modifier = Modifier.size(56.dp),
                data = url,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, style = MaterialTheme.typography.h3, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun ExercizeItem(url: String, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CoilImage(
            modifier = Modifier.size(88.dp),
            data = url,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            requestBuilder = { transformations(CircleCropTransformation()) }
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp)
        )
    }
}

val testCollections = listOf(
    "Short mantras" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Nature meditations" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stress and anxiety" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Self-massage" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Short mantras" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Nature meditations" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stress and anxiety" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Self-massage" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Short mantras" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Nature meditations" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stress and anxiety" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Self-massage" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Short mantras" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Nature meditations" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stress and anxiety" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Self-massage" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
).chunked(2)

val testExersizes = listOf(
    "Inversions" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Quick yoga" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stretching" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Tabata" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Inversions" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Quick yoga" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stretching" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Tabata" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Inversions" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Quick yoga" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stretching" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Tabata" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
    "Inversions" to "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Quick yoga" to "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Stretching" to "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
    "Tabata" to "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260",
)
