package com.example.myapplication.dao
import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.models.ComentarioConUsuario

@Dao
interface ComentarioDao {
    @Query("""
        SELECT * FROM Comentario 
        INNER JOIN Usuario ON Comentario.idUsuario = Usuario.id
        WHERE Comentario.idEdificacion = :idEdificacion
    """)
    suspend fun getComentariosByEdificacion(idEdificacion: Int): List<ComentarioConUsuario>
}
