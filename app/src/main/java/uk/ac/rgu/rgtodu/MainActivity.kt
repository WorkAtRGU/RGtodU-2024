package uk.ac.rgu.rgtodu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.rgu.rgtodu.ui.theme.RGtodUTheme

import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.rgu.rgtodu.ui.EditTaskScreen
import uk.ac.rgu.rgtodu.ui.RGtodUApp
import uk.ac.rgu.rgtodu.ui.TaskViewModel
import uk.ac.rgu.rgtodu.ui.TasksScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)
        setContent {
            RGtodUTheme{
                RGtodUApp()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                ) {
//                    val taskViewModel: TaskViewModel = viewModel()
//                    TasksScreen(taskViewModel.tasksUiState)
//                   // uk.ac.rgu.rgtodu.EditTaskScreen()
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterTaskPreview() {
    RGtodUTheme {
//        EditTaskScreen()
    }
}
