package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.entities.Calificacion
import com.example.myapplication.entities.Comentario
import com.example.myapplication.entities.Edificacion
import com.example.myapplication.entities.Usuario
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAbrirConsulta).setOnClickListener {
            val intent = Intent(this, ConsultaActivity::class.java)
            startActivity(intent)
        }
    }
    private fun limpiarBaseDeDatos() {
        val database = (application as App).database

        lifecycleScope.launch {
            database.clearAllTables() // Esta función está incluida en Room para limpiar todas las tablas
        }
    }
    private fun generarRegistrosPrueba() {
        val database = (application as App).database

        lifecycleScope.launch {
            // Insertar Usuarios
            val usuario1 = Usuario(0, "user1", "user1@example.com", "password123")
            val usuario2 = Usuario(0, "user2", "user2@example.com", "password456")
            val usuario3 = Usuario(0, "user3", "user3@example.com", "password789")
            database.usuarioDao().insert(usuario1)
            database.usuarioDao().insert(usuario2)
            database.usuarioDao().insert(usuario3)

            // Insertar Edificaciones
            val edificacion1 = Edificacion(
                0, "Edificio A", "Descripción A", 45.0, 90.0,
                "imagen1.jpg", "audio1.mp3", "2020-01-01"
            )
            val edificacion2 = Edificacion(
                0, "Edificio B", "Descripción B", 50.0, 100.0,
                "imagen2.jpg", "audio2.mp3", "2021-02-01"
            )
            database.edificacionDao().insert(edificacion1)
            database.edificacionDao().insert(edificacion2)

            // Insertar Comentarios
            val comentario1 = Comentario(0, "Hermoso lugar", "2024-11-20", 1, 1)
            val comentario2 = Comentario(0, "Muy interesante", "2024-11-21", 2, 1)
            val comentario3 = Comentario(0, "Vale la pena visitarlo", "2024-11-22", 3, 2)
            database.comentarioDao().insertAll(comentario1, comentario2, comentario3)

            // Insertar Calificaciones
            val calificacion1 = Calificacion(0, 5, 1, 1)
            val calificacion2 = Calificacion(0, 4, 2, 1)
            val calificacion3 = Calificacion(0, 3, 3, 2)
            database.calificacionDao().insertAll(calificacion1, calificacion2, calificacion3)

            // Confirmación en Logcat
            Log.d("MainActivity", "Datos de prueba insertados con éxito")
        }
    }
}

