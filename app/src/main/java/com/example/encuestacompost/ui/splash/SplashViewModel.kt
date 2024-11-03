package com.example.encuestacompost.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.encuestacompost.daoSQLite.RoomSQLiteDB
import com.example.encuestacompost.entidad.Encuestador

class SplashViewModel(context: Context) : ViewModel() {
    private val _db = Room.databaseBuilder(
        context,
        RoomSQLiteDB::class.java, "BD_ENCUESTA_ROOM"
    ).build()
    private val _encuestadorDao = _db.encuestadorDao()

    suspend fun altaEncuestador(encuestador: Encuestador) {
        _encuestadorDao.alta(encuestador)
    }
}