# RGtodU #
This repository contains the Android Studio project for a basic version of the RGtodU app.

## App Description ##

** Title ** RGtodU

** Promo Text ** - Helping you track and complete all your tasks.

** Functionalities ** -
- The home page, downloads Tasks from a Firebase Realtime database accessed via HTTP, with each Task shown in a LazyList.
- Clicking on a Task from the list, will display a page showing that Task.
- The displayed Task can be Edited or Deleted.
- When Editing a Task, various TextFields are used to show the current value for each Task attribute, which the user can change
- Edits can be saved or cancelled
- Home page also allows user to create a new Task.
- Tasks can also be done with a Timer, or added to Calendar.
- Tasks can be stored locally on a Room database, and in a remote Firebase Realtime Database via HTTP

## App Design ##

###Use cases###
End user
- Create Task
- View a task
- View all tasks
- Edit a task
- Delete a task
- Add Task details to the phone's Calendar app
- Do a task using Clock app service
- Sync data stores (depended on by create, view view all, edit, del tasks). Uses cloud Firebase Realime database service and Device RoomSQL database

todo: insert images of task and user flows for above tasks



### Pages ###
All pages below are implemented as a fragment, hosted in MainActivity, with navigation implemened using Navigation component.

** Data and Every Page **
- Navigation is implemented using a NavController, allowing the user to navigate between the various pages.
- An app bar is shown at the top of each page, with the name of the page and a back button supporting navigation
- Other navigation is completed through clicking Buttons on each page.

The app uses the ViewModel and UI State architecture components, defining a TaskUiState data class,  is used to store the state of each of the current task's attributions using the Flow framework.
- name : String, giving the task's name
- description : String, an extended description of the thing to be done
- priority : String, the selected priority (importance of the task). The options are low, medium, high
- estimated hours : Int, the number of hours of work remaining to complete the task
- deadline : String, the date (formatted according to ISO standard) that the work must be completed by

A ViewModel (TaskViewModel) along with TaskUiState as a MutableStateFlow is used to store the details being entered by the user and handle other data related functions.

** STart page **
todo: insert screenshot of home page
- Start screen when opening app, implemented as a Composable, using a Column for layout.
- Clicking on Button at top navigates to Create Task page.
- Uses a LazyList to display each Task, displaying the name of each Task using a Text composable.
- Clicking on the Task name navigates to the View a Task page.

** Create / Edit task **
todo: insert screenshot of create task / edit task page
- The same Composable is used, with details of the Task attributes returned by TaskViewModel displayed (when creating a new Task, this is nothing)
- Each attribute (and label) are arranged within a Column, which is scrollable.
- Clicking Cancel button resets the TaskViewModel and navigates to the Start page
- Clicking Save button has TaskViewModel store the Task in the TaskUIState (or update the Task details in the list of all tasks if editing an existing task) and navigates to the Start page.

** View a task **
todo: insert screenshot
- Screen to display details of the Task selected in the Home page list, as returned by ViewModel
- Uses a Composable which returns a Row of a Text showing label of the Task attribute (name, description, etc) and a Text showing the value.
- Each Row is arranged vertically in a Column.
- Texts are used, nothing is editable.
- Includes Buttons to Edit the Task (which navigates to the Edit Task page), and Delete Task (which deletes from the list of tasks).

** Web service **
Uses Firebase Realtime API, providing HTTP API for create, get, update, delete tasks, exchanged using JSON with keys:
- task id
- objective (the task description)
- hours (the estimated hours)
- priority
- deadline

** Data store **
Stores data in a single RoomsQL database table, with columns
- task id
- description
- estimatedHours
- priority
- deadline

## Tool Review ##
TODO: some thoughts on using Android Studio, the debugger, virtual devices, other tools used, e.g. generative AI

## Security Review ##
TODO: evaluation of the Privacy and Security Guidelines relevance to this app.