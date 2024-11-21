package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.ConsultaAdapter
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.entities.Calificacion
import com.example.myapplication.entities.Comentario
import com.example.myapplication.entities.Edificacion
import com.example.myapplication.entities.Usuario
import kotlinx.coroutines.launch

class ConsultaActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var consultaAdapter: ConsultaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar la base de datos
        database = (application as App).database

        // Botón para cargar datos de prueba
        findViewById<Button>(R.id.btnCargarDatos).setOnClickListener {
            generarRegistrosPrueba() // Luego cargar los datos de prueba
        }

        // Botones para ejecutar consultas
        findViewById<Button>(R.id.btnConsultaUnoAUno).setOnClickListener {
            realizarConsultaUnoAUno()
        }

        findViewById<Button>(R.id.btnConsultaUnoAMuchos).setOnClickListener {
            realizarConsultaUnoAMuchos()
        }

        findViewById<Button>(R.id.btnConsultaMuchosAMuchos).setOnClickListener {
            realizarConsultaMuchosAMuchos()
        }
    }

    // Función para generar datos de prueba
    private fun generarRegistrosPrueba() {
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

            Log.d("ConsultaActivity", "Datos de prueba generados con éxito")
        }
    }

    // 1:1 --- Un comentario solo puede contener una calificación
    private fun realizarConsultaUnoAUno() {
        lifecycleScope.launch {
            val comentarioConCalificacion = database.comentarioDao()
                .getComentarioConCalificacion(1) // Cambiar ID según sea necesario
            val encabezado = "Consulta 1:1 - Comentario y Calificación"
            consultaAdapter = ConsultaAdapter(listOf(encabezado) + comentarioConCalificacion.map {
                "Comentario: ${it.comentario.contenido}\nCalificación: ${it.usuario.username}"
            })
            recyclerView.adapter = consultaAdapter
        }
    }

    // 1:N --- Obtener todos los comentarios de una edificación
    private fun realizarConsultaUnoAMuchos() {
        lifecycleScope.launch {
            val comentarios = database.comentarioDao()
                .getComentariosByEdificacion(1) // Cambiar ID según sea necesario
            val encabezado = "Consulta 1:N - Comentarios de Edificación"
            consultaAdapter = ConsultaAdapter(listOf(encabezado) + comentarios.map {
                "Comentario: ${it.comentario.contenido}\nEdificación ID: ${it.comentario.idEdificacion}"
            })
            recyclerView.adapter = consultaAdapter
        }
    }

    // N:M --- Usuarios y sus calificaciones
    private fun realizarConsultaMuchosAMuchos() {
        lifecycleScope.launch {
            val usuariosConCalificaciones = database.calificacionDao()
                .getUsuariosConCalificaciones()
            val encabezado = "Consulta N:M - Usuarios y Calificaciones"
            consultaAdapter = ConsultaAdapter(listOf(encabezado) + usuariosConCalificaciones.map {
                "Usuario: ${it.usuario.username}\nCalificación: ${it.calificacion.valor}"
            })
            recyclerView.adapter = consultaAdapter
        }
    }
}




