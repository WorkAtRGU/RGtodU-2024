package uk.ac.rgu.rgtodu.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.ac.rgu.rgtodu.data.DataSource
import uk.ac.rgu.rgtodu.data.TaskUiState

/**
 * [TaskViewModel] holds information about a Task being created / edited.
 */
class TaskViewModel : ViewModel() {




    /**
     * Task state for this task
     */
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()


    var taskName by mutableStateOf("")
        private set
    var taskDescription by mutableStateOf("")
        private set
    var taskPriority by mutableStateOf("")
        private set
    var taskEstimatedHours  by mutableStateOf( 0)
        private set
    var taskDeadline by mutableStateOf("")
        private set

    /**
     * Updates the task name.
     *
     * @param taskName The new task name to set.
     *
     * This method updates the `taskName` property and ensures that the new value
     * is reflected in the ViewModel.
     */
    fun updateTaskName(taskName: String) {
        this.taskName = taskName
    }

    /**
     * Updates the task description.
     *
     * @param taskDescription The new task description to set.
     *
     * This method updates the `taskDescription` property and ensures that the new value
     * is reflected in the ViewModel.
     */
    fun updateTaskDescription(taskDescription: String) {
        this.taskDescription = taskDescription
    }


    /**
     * Updates the task priority.
     *
     * @param taskPriority The new task priority to set.
     *
     * This method updates the `taskPriority` property and ensures that the new value
     * is reflected in the ViewModel.
     */
    fun updateTaskPriority(taskPriority: String) {
        this.taskPriority = taskPriority
    }


    /**
     * Updates the number of hours it is estimated to take to complete the task..
     *
     * @param taskEstimatedHours The new task priority to set.
     *
     * This method updates the `taskEstimatedHours` property and ensures that the new value
     * is reflected in the ViewModel.
     */
    fun updateTaskEstimatedHours(taskEstimatedHours: Int) {
        this.taskEstimatedHours = taskEstimatedHours
    }


    /**
     * Updates the task deadline.
     *
     * @param taskDeadline The new task priority to set.
     *
     * This method updates the `taskPriority` property and ensures that the new value
     * is reflected in the ViewModel.
     */
    fun updateTaskDeadline(taskDeadline: String) {
        this.taskDeadline = taskDeadline
    }

    /**
     * Stores details of the task in the UI State, as defined by the various task properties
     */
    fun storeTask(){
        _uiState.update { currentState ->
            currentState.copy(
                taskName = this.taskName,
             taskDescription = this.taskDescription,
             selectedPriorityOption = this.taskPriority,
             taskEstimatedHours = this.taskEstimatedHours,
             taskDeadline = this.taskDeadline
            )
        }

    }


}