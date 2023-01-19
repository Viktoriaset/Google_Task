package com.example.google_task.task_list

import android.location.GnssAntennaInfo.Listener
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.task.RoomTaskDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val roomTaskListDataSource: RoomTaskListDataSource,
    private val roomTaskDataSource: RoomTaskDataSource
) : ViewModel() {

    var listsLiveData = roomTaskListDataSource.loudAllTaskLists()

    fun insertTask(task: TaskEntity){
        roomTaskDataSource.insertNewTask(task)
    }

    fun insertList(list: ListEntity){
        roomTaskListDataSource.insertNewTaskList(list)
    }

    fun getListByUUID(uuidString: String): LiveData<ListEntity>{
        val uuid = UUID.fromString(uuidString)
        return roomTaskListDataSource.getTaskByUUID(uuid)
    }

    fun updateList(list: ListEntity){
        roomTaskListDataSource.updateTaskList(list)
    }

    fun deleteList(list: ListEntity){
        roomTaskListDataSource.deleteTaskList(list)
    }
}