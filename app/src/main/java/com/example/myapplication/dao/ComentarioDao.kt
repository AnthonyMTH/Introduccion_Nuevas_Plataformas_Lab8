package com.example.myapplication.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.entities.Comentario
import com.example.myapplication.entities.Edificacion
import com.example.myapplication.entities.Usuario
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
    suspend fun insertAll(vararg comentarios: Comentario)

    //1:1 --- Un comentario solo puede contener una calificacion
    @Query("""
        SELECT * FROM Comentario
        INNER JOIN Calificacion ON Comentario.idUsuario = Calificacion.idUsuario
        WHERE Comentario.id = :idComentario
    """)
    suspend fun getComentarioConCalificacion(idComentario: Int): List<ComentarioConUsuario>

    //1:M
    @Query("""
    SELECT DISTINCT Usuario.* FROM Usuario
    INNER JOIN Calificacion ON Usuario.id = Calificacion.idUsuario
    WHERE Calificacion.idEdificacion = :idEdificacion
    """)
    suspend fun getUsuariosQueCalificaron(idEdificacion: Int): List<Usuario>

    //N:M
    @Query("""
    SELECT * FROM Edificacion
    INNER JOIN Comentario ON Edificacion.id = Comentario.idEdificacion
    """)
    suspend fun getEdificacionesConComentarios(): List<Edificacion>


}
