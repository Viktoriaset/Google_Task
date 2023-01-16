package com.example.google_task.task

import androidx.lifecycle.LiveData
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity

interface TaskDataSource {

    fun loudAllTaskByTaskList(listId: Int) : LiveData<List<TaskEntity>>

    fun loudAllTasks(): LiveData<List<TaskEntity>>

    fun insertNewTask(task: TaskEntity)

    fun updateTask(task: TaskEntity)

     fun deleteTask(task: TaskEntity)
}