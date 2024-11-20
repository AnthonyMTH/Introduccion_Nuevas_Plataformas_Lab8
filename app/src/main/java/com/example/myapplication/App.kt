package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.database.AppDatabase

class App : Application() {

    // Base de datos de Room
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        // inicializar Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "patrimonio_vivo_db"
        ).build()
    }
}
