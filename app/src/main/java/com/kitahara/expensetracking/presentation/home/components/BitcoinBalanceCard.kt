package com.kitahara.expensetracking.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kitahara.expensetracking.R

@Composable
fun BitcoinBalanceCard(
    bitcoinCount: Float,
    showDialog: () -> Unit,
    navigateTransactionScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            14.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        Card(
            Modifier
                .fillMaxWidth()
                .height(130.dp),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .weight(3.5f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier
                    .weight(2f)
                    .padding(start = 12.dp)) {
                    Text(
                        text = stringResource(R.string.balance),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp),
                        text = "$bitcoinCount",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Box(
                    modifier = Modifier.weight(.5f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = showDialog
                    ) {
                        Icon(
                            modifier = Modifier.size(45.dp),
                            imageVector = Icons.Rounded.Add,
                            contentDescription = stringResource(R.string.add_bitcoin)
                        )
                    }
                }
            }
        }

        ElevatedButton(
            onClick = {
                navigateTransactionScreen()
            }
        ) {
            Text(
                text = stringResource(R.string.add_transaction),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview
fun BitcoinBalanceCardPreview(
) {
    BitcoinBalanceCard(
        bitcoinCount = 0.1233f, {}, {}
    )
}