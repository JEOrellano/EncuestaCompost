package com.example.encuestacompost.entidad.enumsEntidad

enum class EAntiguedad(
    val id: Int,
    val descripcion: String
) {
    MENOS_DE_1_ANIO(1, "Menos de 1 año"),
    DE_1_A_3_ANIOS(2, "De 1 a 3 años"),
    DE_4_A_7_ANIOS(3, "De 4 a 7 años"),
    DE_8_A_10_ANIOS(4, "De 8 a 10 años"),
    MAS_DE_10_ANIOS(5, "Más de 10 años")
}