package uk.ac.rgu.rgtodu

import android.app.DatePickerDialog
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uk.ac.rgu.rgtodu.ui.TaskViewModel
import java.util.Calendar
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.rgu.rgtodu.data.DataSource


@Composable
fun EditTaskScreen(taskViewModel: TaskViewModel = viewModel()) {

    val priorityOptions = DataSource.priorityOptions.map { stringResource(it) }


    // To handle date picker dialog
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            taskViewModel.updateTaskDeadline("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Task Name input
        Text(text = "Task Name")
        OutlinedTextField(
            value = taskViewModel.taskName,
            onValueChange = { taskViewModel.updateTaskName(it)},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface)
        )

        // Task Description input
        Text(text = "Description")
        OutlinedTextField(
            value = taskViewModel.taskDescription,
            onValueChange = { taskViewModel.updateTaskDescription(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            maxLines = 5
        )

        // Task Priority selection
        Text(text = "Priority")
        priorityOptions.forEach { option ->
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (option == taskViewModel.taskPriority),
                    onClick = { taskViewModel.updateTaskPriority(option) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }

        // Task Estimated Hours input
        Text(text = "Estimated Hours")
        OutlinedTextField(
            value = taskViewModel.taskEstimatedHours.toString(),
            onValueChange = {
                if (!"".equals(it))
                    taskViewModel.updateTaskEstimatedHours(it.toInt()?:0) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Task Deadline input
        Text(text = "Deadline")
        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (taskViewModel.taskDeadline.isEmpty()) "Select Date" else taskViewModel.taskDeadline)
        }

        Row(){
            // Store task
            Button(
                onClick = { taskViewModel.storeTask() }
            ) {
                Text(text = "Cancel")
            }
            // Store task
            Button(
                onClick = { taskViewModel.storeTask() }
            ) {
                Text(text = "Store Task")
            }
        }

    }
}
