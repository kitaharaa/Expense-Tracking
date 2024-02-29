package com.kitahara.expensetracking.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxSize(),
        text = "Home Screen"
    )
}