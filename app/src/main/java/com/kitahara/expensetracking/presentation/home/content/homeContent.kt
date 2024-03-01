package com.kitahara.expensetracking.presentation.home.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.domain.entity.TransactionData
import com.kitahara.expensetracking.presentation.home.components.BitcoinBalanceCard
import com.kitahara.expensetracking.presentation.home.components.TransactionItem

fun LazyListScope.homeContent(
    bitcoinAmount: Float,
    transactionPagingItem: LazyPagingItems<TransactionData>,
    navigateTransactionScreen: () -> Unit,
    showDialog: () -> Unit,
) {
    item {
        BitcoinBalanceCard(
            bitcoinCount = bitcoinAmount,
            showDialog = showDialog,
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