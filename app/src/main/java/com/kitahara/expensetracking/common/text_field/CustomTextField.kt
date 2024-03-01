package com.kitahara.expensetracking.common.text_field

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.kitahara.expensetracking.R

@Composable
fun CustomTextField(stateText: String, changeState: (String) -> Unit) {
    OutlinedTextField(
        value = stateText,
        label = {
            Text(
                text = stringResource(R.string.amount),
                style = MaterialTheme.typography.bodySmall
            )
        },
        onValueChange = {
            if (it.length < 9) {
                if (it.isEmpty()) {
                    changeState(it)
                } else {
                    if (it.startsWith("00").not()) {
                        val isFloat = it.toFloatOrNull()
                        Log.e("FloatConvert", "isFloat = $isFloat, input = $it")

                        if (isFloat != null)
                            changeState(it)
                    }
                }
            }
        },
        singleLine = true,

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}