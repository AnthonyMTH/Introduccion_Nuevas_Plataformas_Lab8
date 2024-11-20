package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.dao.CalificacionDao
import com.example.myapplication.dao.ComentarioDao
import com.example.myapplication.dao.EdificacionDao
import com.example.myapplication.dao.UsuarioDao
import com.example.myapplication.entities.Edificacion
import com.example.myapplication.entities.Usuario
import com.example.myapplication.entities.Comentario
import com.example.myapplication.entities.Calificacion


@Database(entities = [Edificacion::class, Usuario::class, Comentario::class, Calificacion::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun edificacionDao(): EdificacionDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun comentarioDao(): ComentarioDao
    abstract fun calificacionDao(): CalificacionDao
}