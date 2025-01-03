package com.example.labs_mobile.labs.lab6.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labs_mobile.labs.lab6.components.ElectricAppliancesTab
import com.example.labs_mobile.labs.lab6.components.LargeElectricAppliancesTab
import com.example.labs_mobile.labs.lab6.components.ShopGroupCalculationsComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricalLoadCalculationsScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Прилади", "Великі прилади", "Загальні показники цеху")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Електричні навантаження") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTabIndex) {
                0 -> ElectricAppliancesTab()
                1 -> LargeElectricAppliancesTab()
                2 -> ShopGroupCalculationsComponent()
            }
        }
    }
}

