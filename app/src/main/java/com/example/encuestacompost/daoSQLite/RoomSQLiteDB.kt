package com.example.encuestacompost.daoSQLite

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.encuestacompost.entidad.Encuesta
import com.example.encuestacompost.entidad.Encuestador

@Database(entities = [Encuestador::class, Encuesta::class], version = 1)
abstract class RoomSQLiteDB: RoomDatabase() {
    abstract fun encuestadorDao(): EncuestadorDao
    abstract fun encuestaDao(): EncuestaDao
}