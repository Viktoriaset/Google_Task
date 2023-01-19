package com.example.google_task.task

import androidx.lifecycle.*
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.task.data_source.RoomTaskDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val roomTaskDataSource: RoomTaskDataSource,
) : ViewModel() {

    lateinit var tasksLiveData : LiveData<List<TaskEntity>>
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

    fun setListId(listId: String){
        tasksLiveData = roomTaskDataSource.loudAllTaskByTaskList(listId)
    }

}