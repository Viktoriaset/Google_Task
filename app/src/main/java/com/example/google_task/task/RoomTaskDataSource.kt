package com.example.google_task.task

import com.example.google_task.data.dao.TaskDao
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity

class RoomTaskDataSource(private val taskDao: TaskDao) : TaskDataSource {

    override fun loudAllTaskByTaskList(list: ListEntity): List<TaskEntity> {
        return taskDao.getAllByList(list.listId)
    }

}