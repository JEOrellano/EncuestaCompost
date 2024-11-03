package com.example.encuestacompost.entidad.enumsEntidad

enum class EEstudio(
    val id: Int,
    val descripcion: String
) {
    PRIMARIA(1, "Primaria"),
    SECUNDARIA(2, "Secundaria"),
    TERCIARIA(3, "Terciaria"),
    UNIVERSITARIA(4, "Universitaria"),
    POST_GRADO(5, "Postgrado"),
    SIN_FINALIZAR(6, "sin finalizar")
}