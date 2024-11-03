package com.example.encuestacompost.ui.signin

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.encuestacompost.R
import com.example.encuestacompost.daoSQLite.RoomSQLiteDB
import kotlinx.coroutines.delay

class SigInViewModel(context: Context) : ViewModel() {
    private val _db = Room.databaseBuilder(
        context,
        RoomSQLiteDB::class.java, context.getString(R.string.BD_ENCUESTA_ROOM)
    ).build()
    private val _encuestadorDao = _db.encuestadorDao()

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _signInEnabled = MutableLiveData<Boolean>()
    val signInEnabled: LiveData<Boolean> = _signInEnabled
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSigInChanged(username: String, password: String) {
        _username.value = username
        _password.value = password
        _signInEnabled.value = isValidUsername(username) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length >= 4

    private fun isValidUsername(username: String): Boolean = username.length >= 3

    suspend fun onSigInSelected(
        navigateToMain: () -> Unit,
        navigateBack: () -> Boolean,
        context: Context
    ) {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
        if (signInEnabled.value == true) {
            //Validar usuario y contraseña
            var mensajeToast: String = ""
            if (!(_encuestadorDao.login(
                    username.value.toString(),
                    password.value.toString()
                )?.cue.isNullOrEmpty())
            ) {
                try {
                    //Navegar a la siguiente pantalla
                    mensajeToast = "Bienvenido ${username.value}"
                    navigateBack()
                    navigateToMain()
                    val sharedPref = context.getSharedPreferences(
                        context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
                        Context.MODE_PRIVATE
                    ) ?: return
                    with(sharedPref.edit()) {
                        putString(
                            context.getString(R.string.com_example_encuestacompost_USERNAME_KEY),
                            username.value
                        )
                        apply()
                    }
                } catch (e: Exception) {
                    // Mostrar mensaje de error
                    mensajeToast =
                        "Error al navegar a la siguiente pantalla, intente de nuevo porfavor"
                    println(e.message)
                    Log.e("Error", e.printStackTrace().toString())
                }
            } else {
                // Mostrar mensaje de error
                mensajeToast = "Usuario o contraseña incorrectos, intente de nuevo porfavor"
            }
            Toast.makeText(context, mensajeToast, Toast.LENGTH_SHORT).show()
        }
    }
}