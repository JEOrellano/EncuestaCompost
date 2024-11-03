package com.example.encuestacompost.entidad.enumsEntidad

enum class EConforme(
    val id: Int,
    val descripcion: String
) {
    SI(1, "SI"),
    NO(2, "NO"),
    NO_LO_SE(3, "No lo se")
}