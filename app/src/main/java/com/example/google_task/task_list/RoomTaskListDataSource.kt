package com.example.google_task.task_list

import androidx.lifecycle.LiveData
import com.example.google_task.data.dao.ListDao
import com.example.google_task.data.entities.ListEntity
import kotlinx.coroutines.flow.Flow

class RoomTaskListDataSource (private val taskListDao: ListDao) : TaskListDataSource {

    override fun loudAllTaskLists(): LiveData<List<ListEntity>> {
        return taskListDao.getAllTaskLists()
    }

    override fun insertNewTaskList(listEntity: ListEntity) {
        taskListDao.insertList(listEntity)
    }

    override fun updateTaskList(listEntity: ListEntity) {
        taskListDao.updateList(listEntity)
    }

    override fun deleteTaskList(listEntity: ListEntity) {
        taskListDao.deleteList(listEntity)
    }
}