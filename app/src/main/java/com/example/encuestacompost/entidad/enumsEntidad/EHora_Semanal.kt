package com.example.encuestacompost.entidad.enumsEntidad

enum class EHora_Semanal(
    val id: Int,
    val descripcion: String
) {
    MENOR_DE_10(1, "< 10"),
    DE_10_A_32(2, "10 a 32"),
    MAYOR_DE_32(3, "> 32")
}