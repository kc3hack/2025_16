package com.example.test.ui.theme.input

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import java.util.Calendar

@ExperimentalMaterial3Api
@Composable
fun DialUseStateExample(
        onConfirm: (TimePickerState) -> Unit,
        onDismiss: () -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState =
            rememberTimePickerState(
                    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                    initialMinute = currentTime.get(Calendar.MINUTE),
                    is24Hour = true,
            )
    LaunchedEffect(timePickerState.hour, timePickerState.minute) { onConfirm(timePickerState) }

    Column {
        TimeInput(
                state = timePickerState,
        )
    }
}
