package com.example.encuestacompost.entidad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Encuestador(
    @PrimaryKey
    val cue: String,
    val password: String
)
