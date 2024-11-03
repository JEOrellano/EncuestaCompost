package com.example.encuestacompost.entidad.enumsEntidad

enum class EEdad(
    val id: Int,
    val descripcion: String
) {
    MENOR_DE_18(1, "< de 18"),
    DE_18_A_35(2, "18 a 35"),
    DE_36_A_50(3, "36 a 50"),
    DE_51_A_65(4, "51 a 65"),
    MAYOR_DE_65(5, "> de 65")
}