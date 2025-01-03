package com.example.labs_mobile
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labs_mobile.labs.lab1.task1.screens.Lab1Task1Screen
import com.example.labs_mobile.labs.lab1.task2.screens.Lab1Task2Screen
import com.example.labs_mobile.labs.lab2.task1.screens.EmissionCalculatorScreen
import com.example.labs_mobile.labs.lab3.screens.SolarCalculatorScreen
import com.example.labs_mobile.labs.lab4.screens.ShortCircuitCalculatorScreen
import com.example.labs_mobile.labs.lab5.screens.ElectricalCalculationsScreen
import com.example.labs_mobile.labs.lab6.screens.ElectricalLoadCalculationsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavigation()
            }
        }
    }
}

data class Lab(val id: Int, val name: String)
data class Task(val id: String, val name: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lab_list") {
        composable("lab_list") {
            LabListScreen(navController)
        }
        composable("task_list/{labId}") { backStackEntry ->
            val labId = backStackEntry.arguments?.getString("labId") ?: ""
            val taskListRoute = NavigationMappings.labToTaskList[labId] ?: "lab_list" // Default route if labId is not found
            TaskListScreen(navController, labId, taskListRoute)
        }
        composable("task_details/{labId}/{taskId}") { backStackEntry ->
            val labId = backStackEntry.arguments?.getString("labId") ?: ""
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            TaskDetailsScreen(taskId, labId)
        }
    }
}

@Composable
fun LabListScreen(navController: NavController) {
    val labs = listOf(
        Lab(1, "Lab 1"),
        Lab(2, "Lab 2"),
        Lab(3, "Lab 3"),
        Lab(4, "Lab 4"),
        Lab(5, "Lab 5"),
        Lab(6, "Lab 6"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(labs) { lab ->
            Button(
                onClick = { navController.navigate("task_list/${lab.id}") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Task List for ${lab.name}")
            }
        }
    }
}

@Composable
fun TaskButton(taskId: String, taskName: String, labId: String, navController: NavController) {
    Button(
        onClick = { navController.navigate("task_details/$labId/$taskId") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = taskName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskListScreen(navController: NavController, labId: String, taskListRoute: String) {
    val tasks: List<Task> = remember(labId) {
        when (labId) {
            "1" -> listOf(
                Task("1", "Task 1"),
                Task("2", "Task 2")
            )
            "2" -> listOf(
                Task("1", "Task 1")
            )
            "3" -> listOf(
                Task("1", "Task 1")
            )
            "4" -> listOf(
                Task("1", "Task 1")
            )
            "5" -> listOf(
                Task("1", "Task 1")
            )
            "6" -> listOf(
                Task("1", "Task 1")
            )
            else -> emptyList()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskButton(
                taskId = task.id,
                taskName = task.name,
                labId = labId,
                navController = navController
            )
        }
    }
}

private val lab1TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { Lab1Task1Screen() },
    "2" to { Lab1Task2Screen() }
)

private val lab2TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { EmissionCalculatorScreen() }
)

private val lab3TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { SolarCalculatorScreen() }
)

private val lab4TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { ShortCircuitCalculatorScreen() }
)

private val lab5TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { ElectricalCalculationsScreen() }
)

private val lab6TaskMapping: Map<String, @Composable () -> Unit> = mapOf(
    "1" to { ElectricalLoadCalculationsScreen() }
)


@Composable
fun TaskDetailsScreen(taskId: String, labId: String) {
    when (labId) {
        "1" -> lab1TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        "2" -> lab2TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        "3" -> lab3TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        "4" -> lab4TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        "5" -> lab5TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        "6" -> lab6TaskMapping[taskId]?.invoke() ?: Text("Unknown Task")
        else -> Text("Unknown Lab")
    }
}