package uk.ac.rgu.rgtodu.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.rgu.rgtodu.data.Task
import uk.ac.rgu.rgtodu.network.TaskApi
import uk.ac.rgu.rgtodu.data.TaskUiState
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface TasksUiState {
    data class Success(val tasks: List<Task>) : TasksUiState
    object Error : TasksUiState
    object Loading : TasksUiState
}


/**
 * [TaskViewModel] holds information about a Task being created / edited.
 */
class TaskViewModel : ViewModel() {

    /**
     * The mutable State that stores the status of the most recent requst
     */
    var tasksUiState: TasksUiState by mutableStateOf(TasksUiState.Loading)
        private set

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

    var task by mutableStateOf(Task())
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
        this.task =  Task(
            name = this.taskName,
            description = this.taskDescription,
            priority = this.taskPriority,
            estimatedHours = this.taskEstimatedHours,
            deadline = this.taskDeadline)

        _uiState.update { currentState ->
            var newTaskList = currentState.taskList.toMutableStateList()
            newTaskList.add(this.task)

            currentState.copy(
                task = this.task,
                taskList = newTaskList
            )
        }
    }

    fun setCurrentTask(t : Task){
        this.task = t
        _uiState.update { currentState ->
            currentState.copy(
                task = t
            )
        }
    }

    fun setTaskList(taskList : List<Task>){
        _uiState.update { currentState ->
            currentState.copy(
                taskList = taskList
            )
        }
    }

    /**
     * Call getTasks() on itit so we can display them
     */
    init{
        getTasks()
    }

    private fun getTasks(){
        viewModelScope.launch {
            tasksUiState =
            try {
                val listResult = TaskApi.retrofitService.getTasks()
                setTaskList(listResult)
                TasksUiState.Success(listResult)
            } catch (e :IOException){
                TasksUiState.Error
            }

        }
    }

    fun removeTaskFromList(task: Task) {
        var newTaskList = uiState.value.taskList.toMutableList()
        newTaskList.remove(task)
        setTaskList(newTaskList)
    }

    fun resetCurrentTask() {
        this.taskName = ""
        this.taskDescription = ""
        this.taskPriority = ""
        this.taskEstimatedHours = 0
        this.taskDeadline = ""
        setCurrentTask(Task())
    }

    fun updateTask() {
        TODO("Not yet implemented")
    }

}