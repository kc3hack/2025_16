package com.example.test.ui.theme.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
@ExperimentalMaterial3Api
@Composable
fun DatePickerDocked() {
    var showDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
                value = selectedDate,
                onValueChange = {},
                label = { Text("DOB") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                        Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().height(64.dp)
        )

        if (showDatePicker.value) {
            Popup(onDismissRequest = { showDatePicker.value = false }, alignment = Alignment.TopStart) {
                Box(
                        modifier =
                                Modifier.fillMaxWidth()
                                        .offset(y = 64.dp)
                                        .shadow(elevation = 4.dp)
                                        .background(MaterialTheme.colorScheme.surface)
                                        .padding(16.dp)
                ) { DatePicker(state = datePickerState, showModeToggle = false) }
            }
        }
    }
}
