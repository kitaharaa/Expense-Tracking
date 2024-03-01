@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.kitahara.expensetracking.presentation.transaction

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kitahara.expensetracking.R
import com.kitahara.expensetracking.common.text_field.CustomTextField
import com.kitahara.expensetracking.presentation.transaction.chip_row.CustomChipRow
import com.kitahara.expensetracking.presentation.transaction.transaction_categories.TransactionCategory
import kotlinx.coroutines.launch

@Composable
fun TransactionAddingScreen(
    modifier: Modifier = Modifier,
    popUp: () -> Unit
) {
    val viewModel: TransactionViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var transactionSum by remember {
        mutableStateOf("")
    }

    var clickedChipCategory: TransactionCategory? by remember {
        mutableStateOf(null)
    }


    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.LightGray),
                title = {
                    Text(
                        text = stringResource(R.string.transaction_adding),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = popUp) {
                        Icon(Icons.Rounded.ArrowBack, stringResource(R.string.go_back))
                    }
                }
            )
        }
    ) { scaffoldPadding ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(transactionSum) {
                transactionSum = it
            }

            Spacer(Modifier.height(40.dp))

            CustomChipRow(
                clickedChipCategory = clickedChipCategory,
                onCategoryChanged = { category: TransactionCategory? ->
                    clickedChipCategory = if (clickedChipCategory == category) null else category
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            FilledTonalButton(
                onClick = {
                    viewModel.saveTransaction(
                        transactionSum = transactionSum,
                        clickedCategory = clickedChipCategory,
                        onComplete = {
                            coroutineScope.launch {
                                popUp()
                            }
                        }
                    ) { message: String ->
                        coroutineScope.launch {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 13.dp),
                    text = stringResource(R.string.add),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
@Preview
fun TransactionScreenPreview() {
    TransactionAddingScreen(Modifier) {}
}