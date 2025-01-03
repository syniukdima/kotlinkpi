//package com.example.labs_mobile
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//
//@Composable
//fun LabListScreen(navController: NavController) {
//    val labs = listOf("1", "2") // List of lab IDs
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = "Select a Lab",
//            style = MaterialTheme.typography.headlineLarge,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        labs.forEach { labId ->
//            Button(
//                onClick = { navController.navigate("task_list/$labId") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                Text(text = "Lab $labId")
//            }
//        }
//    }
////    Column(modifier = Modifier.padding(16.dp)) {
////        Text(
////            text = "Laboratory List",
////            style = MaterialTheme.typography.headlineLarge,
////            modifier = Modifier.padding(bottom = 16.dp)
////        )
////
////        val labs = listOf("Lab 1", "Lab 2", "Lab 3") // Example lab list
////
////        labs.forEachIndexed { index, labName ->
////            Button(
////                onClick = { navController.navigate("task_list/$index") },
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(vertical = 8.dp)
////            ) {
////                Text(text = labName)
////            }
////        }
////    }
//}
