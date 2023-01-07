package com.example.google_task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.google_task.data.entities.List

@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List)

    @Update
    fun updateList(list: List)

    @Delete
    fun deleteList(list: List)

    @Query("SELECT * FROM Lists WHERE listId = :listId")
    fun getById(listId: Int)

}