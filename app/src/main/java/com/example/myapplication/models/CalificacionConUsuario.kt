package com.example.myapplication.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.entities.Calificacion
import com.example.myapplication.entities.Usuario

data class CalificacionConUsuario(
    @Embedded val calificacion: Calificacion,
    @Relation(
        parentColumn = "idUsuario",
        entityColumn = "id"
    )
    val usuario: Usuario
)