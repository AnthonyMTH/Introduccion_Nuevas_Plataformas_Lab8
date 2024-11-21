package com.example.myapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.entities.Calificacion
import com.example.myapplication.entities.Usuario
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
    suspend fun insertAll(vararg calificaciones: Calificacion)


    //1:M
    @Query("""
    SELECT DISTINCT Usuario.* FROM Usuario
    INNER JOIN Calificacion ON Usuario.id = Calificacion.idUsuario
    WHERE Calificacion.idEdificacion = :idEdificacion
    """)
    suspend fun getUsuariosQueCalificaron(idEdificacion: Int): List<Usuario>



    //N:M
    @Query("""
    SELECT * FROM Calificacion
    INNER JOIN Usuario ON Calificacion.idUsuario = Usuario.id
    """)
    suspend fun getUsuariosConCalificaciones(): List<CalificacionConUsuario>


}
