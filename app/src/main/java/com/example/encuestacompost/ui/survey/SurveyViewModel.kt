package com.example.encuestacompost.ui.survey

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.encuestacompost.R
import com.example.encuestacompost.daoSQLite.RoomSQLiteDB
import com.example.encuestacompost.entidad.Encuesta
import com.example.encuestacompost.entidad.enumsEntidad.EAntiguedad
import com.example.encuestacompost.entidad.enumsEntidad.EConforme
import com.example.encuestacompost.entidad.enumsEntidad.EEdad
import com.example.encuestacompost.entidad.enumsEntidad.EEstudio
import com.example.encuestacompost.entidad.enumsEntidad.EHora_Semanal
import com.example.encuestacompost.entidad.enumsEntidad.ERelacion_Contractual
import com.example.encuestacompost.entidad.enumsEntidad.ERubro
import com.example.encuestacompost.entidad.enumsEntidad.ESalario
import com.example.encuestacompost.entidad.enumsEntidad.ESexo
import com.example.encuestacompost.entidad.enumsEntidad.ETrabajo
import kotlinx.coroutines.delay

class SurveyViewModel(context: Context) : ViewModel() {
    private val _db = Room.databaseBuilder(
        context,
        RoomSQLiteDB::class.java, context.getString(R.string.BD_ENCUESTA_ROOM)
    ).build()
    private val _encuestaDao = _db.encuestaDao()

    private val _textoPrueba = MutableLiveData<String>()
    val textoPrueba: LiveData<String> = _textoPrueba

    private val _rbp1 = MutableLiveData<Boolean>()
    val rbp1: LiveData<Boolean> = _rbp1

    private val _spp2 = MutableLiveData<ESexo>()
    val spp2: LiveData<ESexo> = _spp2

    private val _spp3 = MutableLiveData<EEstudio>()
    val spp3: LiveData<EEstudio> = _spp3

    private val _spp4 = MutableLiveData<EEdad>()
    val spp4: LiveData<EEdad> = _spp4

    private val _spp5 = MutableLiveData<ETrabajo>()
    val spp5: LiveData<ETrabajo> = _spp5

    private val _rbp6 = MutableLiveData<Boolean>()
    val rbp6: LiveData<Boolean> = _rbp6

    private val _spp7 = MutableLiveData<ERelacion_Contractual>()
    val spp7: LiveData<ERelacion_Contractual> = _spp7

    private val _spp8 = MutableLiveData<ERubro>()
    val spp8: LiveData<ERubro> = _spp8

    private val _spp9 = MutableLiveData<EHora_Semanal>()
    val spp9: LiveData<EHora_Semanal> = _spp9

    private val _spp10 = MutableLiveData<EAntiguedad>()
    val spp10: LiveData<EAntiguedad> = _spp10

    private val _spp11 = MutableLiveData<ESalario>()
    val spp11: LiveData<ESalario> = _spp11

    private val _spp12 = MutableLiveData<EConforme>()
    val spp12: LiveData<EConforme> = _spp12

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSurveyChanged(
        p1: Boolean,
        p2: ESexo,
        p3: EEstudio,
        p4: EEdad,
        p5: ETrabajo,
        p6: Boolean,
        p7: ERelacion_Contractual,
        p8: ERubro,
        p9: EHora_Semanal,
        p10: EAntiguedad,
        p11: ESalario,
        p12: EConforme
    ) {
        _rbp1.value = p1
        Log.d("SurveyViewModel", "onSurveyChanged: $p1")
        _spp2.value = p2
        Log.d("SurveyViewModel", "onSurveyChanged: ${p2}")
        _spp3.value = p3
        Log.d("SurveyViewModel", "onSurveyChanged: ${p3}")
        _spp4.value = p4
        Log.d("SurveyViewModel", "onSurveyChanged: ${p4}")
        _spp5.value = p5
        Log.d("SurveyViewModel", "onSurveyChanged: ${p5}")
        _rbp6.value = p6
        Log.d("SurveyViewModel", "onSurveyChanged: $p6")
        _spp7.value = p7
        Log.d("SurveyViewModel", "onSurveyChanged: ${p7}")
        _spp8.value = p8
        Log.d("SurveyViewModel", "onSurveyChanged: ${p8}")
        _spp9.value = p9
        Log.d("SurveyViewModel", "onSurveyChanged: ${p9}")
        _spp10.value = p10
        Log.d("SurveyViewModel", "onSurveyChanged: ${p10}")
        _spp11.value = p11
        Log.d("SurveyViewModel", "onSurveyChanged: ${p11}")
        _spp12.value = p12
        Log.d("SurveyViewModel", "onSurveyChanged: ${p12}")
    }

    suspend fun onSurveySelected(context: Context) {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
        _textoPrueba.value = "Hola SurveyScreen"

        val sharedPref = context.getSharedPreferences(
            context.getString(R.string.com_example_encuestacompost_PREFERENCE_FILE_KEY),
            Context.MODE_PRIVATE
        ) ?: return
        val cueSP = sharedPref.getString(
            context.getString(R.string.com_example_encuestacompost_USERNAME_KEY),
            "unknon"
        )

        val encuesta = Encuesta(
            0,
            _spp2.value!!,
            _spp3.value!!,
            _spp4.value!!,
            _spp5.value!!,
            _spp7.value!!,
            _spp8.value!!,
            _spp9.value!!,
            _spp10.value!!,
            _spp11.value!!,
            _spp12.value!!,
            cueSP.toString()
        )

        Log.d("SurveyViewModel", "onSurveySelected: $cueSP")
        Log.d("SurveyViewModel", "onSurveySelected: $encuesta")

        _encuestaDao.alta(encuesta)
    }
}