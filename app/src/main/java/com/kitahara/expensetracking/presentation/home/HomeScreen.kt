@file:OptIn(ExperimentalMaterial3Api::class)

package com.kitahara.expensetracking.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.presentation.home.components.BitcoinAddingDialog
import com.kitahara.expensetracking.presentation.home.components.BitcoinBalanceCard
import com.kitahara.expensetracking.presentation.home.components.TransactionItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateTransactionScreen: () -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val transactionPagingItem = viewModel.getPagedTransactions().collectAsLazyPagingItems()

    val bitcoinAmount by viewModel.provideBitcoinFlow().collectAsState(initial = 0f)

    val bitcoinRate by viewModel.provideBitcoinRateFlow().collectAsState(initial = 0f)

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
            contentPadding = PaddingValues(7.dp)
        ) {

            item {
                BitcoinBalanceCard(
                    bitcoinCount = bitcoinAmount,
                    showDialog = {
                        shouldShowDialog = true
                    },
                    navigateTransactionScreen = navigateTransactionScreen
                )

                HorizontalDivider(Modifier.padding(15.dp))
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, bottom = 7.dp),
                    text = stringResource(R.string.transactions),

                    style = MaterialTheme.typography.titleLarge
                )
            }

            items(
                count = transactionPagingItem.itemCount,
                key = transactionPagingItem.itemKey {
                    it.id
                },
                contentType = transactionPagingItem.itemContentType { "Transactions" }
            ) { index ->
                transactionPagingItem[index]?.let {
                    TransactionItem(modifier = Modifier.fillMaxWidth(), transactions = it)
                }
            }

            item {
                if (transactionPagingItem.itemCount == 0) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        text = stringResource(R.string.no_transactions_yet),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    if (shouldShowDialog)
        BitcoinAddingDialog(
            onDismiss = {
                shouldShowDialog = false
            },
            onConfirm = {
                Log.e("Confirmation", "$it")
                viewModel.addBitcoin(it)

                shouldShowDialog = false
            },
        )
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(modifier = Modifier) {}
}