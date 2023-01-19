package com.example.google_task.task

import com.example.google_task.data.entities.TaskEntity

interface TaskListenerInterface {

    fun updateTask(task: TaskEntity)

    fun deleteTask(task: TaskEntity)

    fun showTaskDescription(task: TaskEntity)
}