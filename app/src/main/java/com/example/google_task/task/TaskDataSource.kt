package com.example.google_task.task

import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity

interface TaskDataSource {
    fun loudAllTaskByTaskList(list: ListEntity) : List<TaskEntity>
}