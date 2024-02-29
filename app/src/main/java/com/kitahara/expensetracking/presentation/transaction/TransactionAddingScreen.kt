@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.expensetracking.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TransactionAddingScreen(
    modifier: Modifier = Modifier,
    popUp: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.LightGray),
                title = {
                    Text(
                        text = "Transaction adding",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = popUp) {
                        Icon(Icons.Rounded.ArrowBack, "Go back")
                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
@Preview
fun TransactionScreenPreview() {
    TransactionAddingScreen(Modifier) {}
}