package com.example.encuestacompost.ui.synchronize

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.encuestacompost.R
import com.example.encuestacompost.daoSQLite.RoomSQLiteDB
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class SynchronizeViewModel(context: Context) : ViewModel() {
    private val _db = Room.databaseBuilder(
        context,
        RoomSQLiteDB::class.java, context.getString(R.string.BD_ENCUESTA_ROOM)
    ).build()
    private val _encuestaDao = _db.encuestaDao()
    private val _encuestadorDao = _db.encuestadorDao()

    val _dbFirebase = FirebaseFirestore.getInstance()

    private val _textoPrueba = MutableLiveData<String>()
    val textoPrueba: LiveData<String> = _textoPrueba

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun onSynchronizeSelected(context: Context) {
        _textoPrueba.value = "Hola SyncronizeScreen"
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false

        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
            Context.MODE_PRIVATE
        ) ?: return
        val cueSP = sharedPref.getString(
            context.getString(R.string.com_example_encuestacompost_USERNAME_KEY),
            "unknon"
        )

        _encuestadorDao.listarEncuestadorConEncuestaPorId(cueSP.toString()).encuestas.forEach {
            _dbFirebase.collection("Encuesta").document().set(
                hashMapOf(
                    "id_sexo" to it.id_sexo.descripcion,
                    "id_estudio" to it.id_estudio.descripcion,
                    "id_edad" to it.id_edad.descripcion,
                    "id_trabajo" to it.id_trabajo.descripcion,
                    "id_relacion_contractual" to it.id_relacion_contractual.descripcion,
                    "id_rubro" to it.id_rubro.descripcion,
                    "id_hora_semanal" to it.id_hora_semanal.descripcion,
                    "id_antiguedad" to it.id_antiguedad.descripcion,
                    "id_salario" to it.id_salario.descripcion,
                    "id_conforme" to it.id_conforme.descripcion,
                    "id_encuestador" to it.id_encuestador
                )
            )
            _encuestaDao.baja(it)
        }
        Toast.makeText(context, "Encuestas sincronizadas", Toast.LENGTH_SHORT).show()
        _textoPrueba.value = "Encuestas sincronizadas"
    }
}