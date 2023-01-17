package com.example.google_task.task

import com.example.google_task.data.entities.TaskEntity

interface TaskListener {

    fun updateTask(task: TaskEntity)

    fun deleteTask(task: TaskEntity)
}