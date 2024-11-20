package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.Comentario
import com.example.myapplication.entities.Usuario

data class ComentarioConUsuario(
    @Embedded val comentario: Comentario,
    @Relation(
        parentColumn = "idUsuario",
        entityColumn = "id"
    )
    val usuario: Usuario
)