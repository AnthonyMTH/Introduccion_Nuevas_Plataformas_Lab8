package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // acceso a la base de datos
        val database = (application as App).database
        val edificacionDao = database.edificacionDao()

        // operaciones con la base de datos
        lifecycleScope.launch {
            val edificaciones = edificacionDao.getEdificacionById(2) // Ejemplo de consulta
            Log.d("MainActivity", "Edificaciones: $edificaciones")
        }
    }
}
