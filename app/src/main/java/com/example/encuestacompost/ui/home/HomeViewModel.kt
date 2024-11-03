package com.example.encuestacompost.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.encuestacompost.R
import com.example.encuestacompost.daoSQLite.RoomSQLiteDB
import kotlinx.coroutines.delay

class HomeViewModel(context: Context) : ViewModel() {
    private val _db = Room.databaseBuilder(
        context,
        RoomSQLiteDB::class.java, context.getString(R.string.BD_ENCUESTA_ROOM)
    ).build()
    private val _encuestadorDao = _db.encuestadorDao()

    private val _contEncuestas = MutableLiveData<String>()
    val contEncuestas: LiveData<String> = _contEncuestas
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun onHomeContarencuestas(context: Context) {
        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
            Context.MODE_PRIVATE
        ) ?: return
        val cueSP = sharedPref.getString(
            context.getString(R.string.com_example_encuestacompost_USERNAME_KEY),
            "unknon"
        )
        _contEncuestas.value = _encuestadorDao.listarEncuestadorConEncuestaPorId(cueSP.toString()).encuestas.size.toString()
        _isLoading.value = false
    }
}