package com.kitahara.expensetracking.presentation.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kitahara.expensetracking.R

@Composable
fun BitcoinAddingDialog(
    onDismiss: () -> Unit,
    onConfirm: (Float) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.enter_bitcoin_count),
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )

                OutlinedTextField(
                    value = text,
                    label = {
                        Text(
                            text = stringResource(R.string.amount),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onValueChange = {
                        if (it.length < 20) {
                            if (it.isEmpty()) {
                                text = it
                            } else {
                                if (it.startsWith("00").not()) {
                                    val isFloat = it.toFloatOrNull()
                                    Log.e("FloatConvert", "isFloat = $isFloat, input = $it")
                                    when (isFloat) {
                                        null -> {}
                                        else -> text = it   //new value
                                    }
                                }
                            }
                        }
                    },
                    singleLine = true,

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.dismiss),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirm(text.toFloat()) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun BitcoinAddingDialogPreview() {
    BitcoinAddingDialog({}, {})
}