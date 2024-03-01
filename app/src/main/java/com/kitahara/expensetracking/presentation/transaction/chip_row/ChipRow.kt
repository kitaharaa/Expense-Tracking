@file:OptIn(ExperimentalLayoutApi::class)

package com.kitahara.expensetracking.presentation.transaction.chip_row

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.presentation.transaction.transaction_categories.TransactionCategory

@Composable
fun CustomChipRow(
    clickedChipCategory: TransactionCategory?,
    onCategoryChanged: (TransactionCategory?) -> Unit
) {
    FlowRow(
        horizontalArrangement = Arrangement
            .spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterHorizontally
            ),

        ) {

        TransactionCategory.entries.forEach { transactionCategory ->
            ChipItem(
                type = transactionCategory,
                clickedChipCategory = clickedChipCategory,
                onClick = onCategoryChanged
            )
        }
    }
}

@Composable
fun ChipItem(
    type: TransactionCategory,
    clickedChipCategory: TransactionCategory?,
    onClick: (TransactionCategory?) -> Unit
) {
    val isSelected = type == clickedChipCategory
    FilterChip(
        onClick = {
            onClick(type)
        },
        label = {
            Text(type.category)
        },
        selected = isSelected,
        leadingIcon = {
            AnimatedVisibility(isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = stringResource(R.string.done_icon),
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}

@Composable
@Preview
fun CustomChipRowPreview() {
    CustomChipRow(clickedChipCategory = TransactionCategory.Taxi) {}
}
