package com.example.myapplication.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.entities.Comentario
import com.example.myapplication.models.ComentarioConUsuario

@Dao
interface ComentarioDao {
    @Query("""
        SELECT * FROM Comentario 
        INNER JOIN Usuario ON Comentario.idUsuario = Usuario.id
        WHERE Comentario.idEdificacion = :idEdificacion
    """)
    suspend fun getComentariosByEdificacion(idEdificacion: Int): List<ComentarioConUsuario>

    @Insert
    suspend fun insertComentario(comentario: Comentario)

    @Update
    suspend fun updateComentario(comentario: Comentario)

    @Delete
    suspend fun deleteComentario(comentario: Comentario)
}
