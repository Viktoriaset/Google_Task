package com.example.google_task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.ListEntity

@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: ListEntity)

    @Update
    fun updateList(list: ListEntity)

    @Delete
    fun deleteList(list: ListEntity)

    @Query("SELECT * FROM Lists WHERE listId = :listId")
    fun getById(listId: Int)

}