package com.example.encuestacompost.entidad

import androidx.room.Embedded
import androidx.room.Relation

data class EncuestadorConEncuesta(
    @Embedded
    val encuestador: Encuestador,
    @Relation(
        parentColumn = "cue",
        entityColumn = "id_encuestador"
    )
    val encuestas: List<Encuesta>
)
