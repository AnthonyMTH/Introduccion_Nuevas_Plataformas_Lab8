package com.example.myapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Edificacion(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nombre: String,
    val descripcion: String,
    val latitud: Double,
    val longitud: Double,
    val imagenes: List<String>, // Usa TypeConverter para listas
    val audio: String,
    val fechaConstruccion: String
)
