package com.example.google_task.task.data_source

import androidx.lifecycle.LiveData
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity

interface TaskDataSource {

    fun loudAllTaskByTaskList(listId: String) : LiveData<List<TaskEntity>>

    fun loudAllTasks(): LiveData<List<TaskEntity>>

    fun loudTasksFavourite(): LiveData<List<TaskEntity>>

    fun insertNewTask(task: TaskEntity)

    fun updateTask(task: TaskEntity)

     fun deleteTask(task: TaskEntity)
}