package com.example.encuestacompost.entidad.enumsEntidad

enum class ERelacion_Contractual(
    val id: Int,
    val descripcion: String
) {
    RELACION_DE_DEPENDENCIA(1, "Relacion de Dependencia"),
    MONOTRIBUTISTA(2, "Monotributista/autonom"),
    CUENTA_PROPISTA(3, "Cuenta Propista (no registrado)"),
    VOLUNTARIADO(4, "Volunatiado"),
    NS_NC(5, "NS/NC"),
}