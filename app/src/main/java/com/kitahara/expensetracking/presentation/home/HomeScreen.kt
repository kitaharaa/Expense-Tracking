@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.expensetracking.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.paging.compose.collectAsLazyPagingItems
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.presentation.home.components.BitcoinAddingDialog
import com.kitahara.expensetracking.presentation.home.content.homeContent
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTransactionScreen: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val transactionPagingItem = viewModel.getPagedTransactions().collectAsLazyPagingItems()
    val bitcoinAmount by viewModel.provideBitcoinFlow().collectAsState(initial = 0f)
    val bitcoinRate by viewModel.provideBitcoinRateFlow().collectAsState(initial = 0f)

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var shouldShowDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.LightGray),
                title = {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = stringResource(R.string.home),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = stringResource(R.string.btc, bitcoinRate),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
        }
    ) { values ->

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(values),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(15.dp)
        ) {
            homeContent(bitcoinAmount,transactionPagingItem, navigateTransactionScreen) {
                shouldShowDialog = true
            }
        }
    }

    if (shouldShowDialog)
        BitcoinAddingDialog(
            onDismiss = {
                shouldShowDialog = false
            },
            onConfirm = {
                Log.e("Confirmation", it)
                viewModel.addBitcoin(it) { message ->
                    coroutineScope.launch {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }

                shouldShowDialog = false
            },
        )

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.updateBitcoinRate()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier) {}
}