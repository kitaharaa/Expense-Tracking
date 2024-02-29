package com.kitahara.expensetracking.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kitahara.expensetracking.presentation.home.components.BitcoinAddingDialog
import com.kitahara.expensetracking.presentation.home.components.BitcoinBalanceCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTransactionScreen: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val bitcoinCount by viewModel.provideBitcoinFlow().collectAsState(initial = 0f)

    var shouldShowDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { values ->

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(values),
            verticalArrangement = Arrangement.Top
        ) {

            item {
                BitcoinBalanceCard(
                    bitcoinCount = bitcoinCount,
                    showDialog = {
                        shouldShowDialog = true
                    },
                    navigateTransactionScreen = navigateTransactionScreen
                )

                HorizontalDivider(Modifier.padding(15.dp))
            }
        }
    }

    if (shouldShowDialog)
        BitcoinAddingDialog(
            onDismiss = {
                shouldShowDialog = false
            },
            onConfirm = {
                viewModel.addBitcoin(it)

                shouldShowDialog = false
            },
        )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier, {})
}