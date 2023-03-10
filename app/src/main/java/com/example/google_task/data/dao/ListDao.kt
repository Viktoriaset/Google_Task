package com.example.google_task.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.ListEntity
import java.util.UUID

@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: ListEntity)

    @Update
    fun updateList(list: ListEntity)

    @Delete
    fun deleteList(list: ListEntity)

    @Query("SELECT * FROM Lists WHERE listId = :listId")
    fun getById(listId: Int) : LiveData<ListEntity>

    @Query("SELECT * FROM Lists")
    fun getAllTaskLists() : LiveData<List<ListEntity>>

    @Query("SELECT * FROM Lists WHERE listId = (:uuid) LIMIT 1")
    fun getListByUUID(uuid: UUID) : LiveData<ListEntity>

}