//package com.example.labs_mobile
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//
//@Composable
//fun TaskListScreen(navController: NavController, labId: String) {
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = "Tasks for Lab $labId",
//            style = MaterialTheme.typography.headlineLarge,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        val tasks = listOf("Task 1", "Task 2") // Example task list
//
//        tasks.forEachIndexed { index, taskName ->
//            Button(
//                onClick = { navController.navigate("task_details/${labId}_${index}") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(text = taskName)
//            }
//        }
//    }
//}
