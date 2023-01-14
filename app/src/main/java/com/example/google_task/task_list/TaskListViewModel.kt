package com.example.google_task.task_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.google_task.data.entities.ListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last


class TaskListViewModel(private val taskListDataSource: TaskListDataSource): ViewModel() {
    val allTaskList : LiveData<List<ListEntity>> =  taskListDataSource.loudAllTaskLists()

}