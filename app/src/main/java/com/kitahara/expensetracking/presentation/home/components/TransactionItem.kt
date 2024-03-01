package com.kitahara.expensetracking.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.domain.entity.TransactionData
import com.kitahara.expensetracking.presentation.ui.theme.Income
import com.kitahara.expensetracking.presentation.ui.theme.Spending

@Composable
fun TransactionItem(modifier: Modifier = Modifier, transactions: TransactionData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
                    .weight(1f),
                imageVector = ImageVector.vectorResource((R.drawable.icon_bitcoin)),
                contentDescription = stringResource(R.string.bitcoin_icon)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.8f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    text = transactions.category,
                    fontSize = 16.sp
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    text = transactions.operationDate,
                    fontSize = 16.sp
                )
            }

            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,

                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = if (transactions.spendAmount.toString().contains("-").not())
                                    Income else Spending
                            )
                        ) {
                            append(transactions.spendAmount.toString())
                        }
                    },
                    fontSize = 16.sp
                )
            }

        }
    }
}

@Composable
@Preview
fun TransactionItemPreview() {
    TransactionItem(
        transactions = TransactionData(
            0,
            "12 лютого, 13:03",
            spendAmount = -0.33f,
            category = "Grocery"
        )
    )
}