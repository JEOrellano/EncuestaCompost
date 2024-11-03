package com.example.encuestacompost.entidad

import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity
data class Encuesta(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val id_sexo: ESexo,
    val id_estudio: EEstudio,
    val id_edad: EEdad,
    val id_trabajo: ETrabajo,
    val id_relacion_contractual: ERelacion_Contractual,
    val id_rubro: ERubro,
    val id_hora_semanal: EHora_Semanal,
    val id_antiguedad: EAntiguedad,
    val id_salario: ESalario,
    val id_conforme: EConforme,
    val id_encuestador: String
)
