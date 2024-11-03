package com.example.encuestacompost.entidad.enumsEntidad

enum class ERubro(
    val id: Int,
    val descripcion: String
) {
    TECNICA(1,"Tecnica"),
    SALUD(2,"Salud"),
    EDUCACION(3,"Educacion"),
    ADMINISTRATIVO(4,"Administrativo"),
    GOBIERNO(5,"Gobierno"),
    COMERCIO(6,"Comercio"),
    CUIDADOS_Y_ASISTENCIA_DOMESTICA(7,"Cuidados y Asistencia Domestica"),
    CONSTRUCCION(8,"Construccion"),
    GASTRONOMIA(9,"Gastronomia"),
    RECICLADO(10,"Reciclado"),
    TRABAJO_DE_PLATAFORMA(11,"Trabajo de plataforma"),
    OTRO(12,"Otro")
}