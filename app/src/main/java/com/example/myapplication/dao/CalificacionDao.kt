package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
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
}
