package com.example.google_task.task_list

import com.example.google_task.data.dao.ListDao

class RoomTaskListDataSource (private val taskListDao: ListDao) : TaskListDataSource {
}