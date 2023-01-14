package com.example.google_task.task

import androidx.lifecycle.*
import com.example.google_task.data.entities.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val roomTaskDataSource: RoomTaskDataSource
) : ViewModel() {


    var tasksLiveData = roomTaskDataSource.loudAllTasks()

    fun insertTask(task: TaskEntity){
        roomTaskDataSource.insertNewTask(task)
    }

}