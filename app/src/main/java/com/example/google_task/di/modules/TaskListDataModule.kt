package com.example.google_task.di.modules

import com.example.google_task.base.AppDatabase
import com.example.google_task.data.dao.ListDao
import com.example.google_task.task.RoomTaskDataSource
import com.example.google_task.task.TaskDataSource
import com.example.google_task.task_list.RoomTaskListDataSource
import com.example.google_task.task_list.TaskListDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TaskListDataModule {

    @Provides
    @Singleton
    fun provideTaskLocalDataSource(roomDatabase: AppDatabase) : TaskDataSource =
        RoomTaskDataSource(roomDatabase.taskDao())

    @Provides
    @Singleton
    fun provideListLocalDataSource(roomDatabase: AppDatabase) : TaskListDataSource =
        RoomTaskListDataSource(roomDatabase.listDao())
}