package com.example.encuestacompost.entidad.enumsEntidad

enum class ESalario(
    val id: Int,
    val descripcion: String
) {
    MENOS_DE_300000(1, "Menos de $300.000"),
    ENTRE_300000_Y_600000(2, "$300.000 ~ $600.000"),
    ENTRE_600000_Y_1M(3, "$600.000 ~ $1M"),
    ENTRE_1M_Y_3M(4, "$1M ~ $3M"),
    MAS_DE_3M(5, "Más de $3M")
}