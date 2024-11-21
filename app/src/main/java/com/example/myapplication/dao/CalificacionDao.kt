package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.entities.Calificacion
import com.example.myapplication.models.CalificacionConUsuario

@Dao
interface CalificacionDao {

    @Transaction
    @Query("""
        SELECT * FROM Calificacion 
        INNER JOIN Usuario ON Calificacion.idUsuario = Usuario.id
        WHERE Calificacion.idEdificacion = :idEdificacion
    """)
    suspend fun getCalificacionesConUsuario(idEdificacion: Int): List<CalificacionConUsuario>

    @Insert
    suspend fun insertCalificacion(calificacion: Calificacion)

    @Update
    suspend fun updateCalificacion(calificacion: Calificacion)

    @Delete
    suspend fun deleteCalificacion(calificacion: Calificacion)
}
