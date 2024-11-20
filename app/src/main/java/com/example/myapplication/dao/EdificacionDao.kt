package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.entities.Edificacion

@Dao
interface EdificacionDao {
    @Insert
    suspend fun insert(edificacion: Edificacion)

    @Update
    suspend fun update(edificacion: Edificacion)

    @Delete
    suspend fun delete(edificacion: Edificacion)

    @Query("SELECT * FROM Edificacion WHERE id = :id")
    suspend fun getEdificacionById(id: Int): Edificacion
}
