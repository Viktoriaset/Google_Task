package com.example.google_task.base

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.google_task.data.dao.ListDao
import com.example.google_task.data.dao.TaskDao
import com.example.google_task.data.entities.ListEntity
import com.example.google_task.data.entities.TaskEntity
import com.example.google_task.di.modules.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [
        ListEntity::class,
        TaskEntity::class],
    version = 5, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao() : ListDao
    abstract fun taskDao() : TaskDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()  {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val taskDao = database.get().taskDao()
            //val taskListDao = database.get().listDao()
            Log.i("Callback creating ", "${taskDao.toString()}" )

            applicationScope.launch {
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
                taskDao.insertTask(TaskEntity(taskText = "first task", listId = 0))
            }
        }
    }
}