package com.example.labs_mobile.labs.lab2.task1.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NaturalGasForm() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(text = "Калькулятор викидів природного газу", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "При спалюванні природного газу тверді частинки не утворюються.",
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            text = "Валові викиди: 0.0 т",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
