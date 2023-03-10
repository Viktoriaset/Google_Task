package com.example.google_task.task_list.data_source

import androidx.lifecycle.LiveData
import com.example.google_task.data.dao.ListDao
import com.example.google_task.data.entities.ListEntity
import java.util.*
import java.util.concurrent.Executors
import javax.inject.Inject

class RoomTaskListDataSource @Inject constructor(
    private val taskListDao: ListDao
    ) : TaskListDataSource {

    private val executor =  Executors.newSingleThreadExecutor()

    override fun loudAllTaskLists(): LiveData<List<ListEntity>> {
        return taskListDao.getAllTaskLists()
    }

    override fun insertNewTaskList(listEntity: ListEntity) {
        executor.execute{
            taskListDao.insertList(listEntity)
        }
    }

    override fun updateTaskList(listEntity: ListEntity) {
        executor.execute{
            taskListDao.updateList(listEntity)
        }
    }

    override fun deleteTaskList(listEntity: ListEntity) {
        executor.execute{
            taskListDao.deleteList(listEntity)
        }
    }

    override fun getTaskByUUID(uuid: UUID): LiveData<ListEntity> {
        return taskListDao.getListByUUID(uuid)
    }
}