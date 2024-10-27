package uk.ac.rgu.rgtodu.data

import java.util.Calendar

/**
 * Data class that represents the current UI state in terms of task details [taskName], [taskDescription], [selectedPriorityOption], [taskEstimatedHours], and [taskDeadline]
 */
data class TaskUiState(
    val taskName: String = "",
    val taskDescription: String = "",
    val selectedPriorityOption: String = "",
    val taskEstimatedHours : Int = 0,
    val taskDeadline : String = ""
    )