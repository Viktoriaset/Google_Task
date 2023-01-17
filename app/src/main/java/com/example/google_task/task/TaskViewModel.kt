package com.example.google_task.task

import androidx.lifecycle.*
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val roomTaskDataSource: RoomTaskDataSource,
) : ViewModel() {

    private var listId: Int = 0

    var tasksLiveData = roomTaskDataSource.loudAllTaskByTaskList(listId)
    var tasksFavouriteLiveData = roomTaskDataSource.loudTasksFavourite()

    fun insertTask(task: TaskEntity){
        roomTaskDataSource.insertNewTask(task)
    }

    fun updateTask(task: TaskEntity){
        roomTaskDataSource.updateTask(task)
    }

    fun deleteTask(task: TaskEntity){
        roomTaskDataSource.deleteTask(task)
    }

    fun setListId(listId: Int){
        this.listId = listId
        tasksLiveData = roomTaskDataSource.loudAllTaskByTaskList(listId)
    }

}