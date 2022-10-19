package com.estudiantes.firebase.converter

import android.util.Log
import com.estudiantes.firebase.entity.EstudianteDtos

class DocumentConverter(
    val id: String = "",
    val name: String = "",
    val city: String = "",
    val age: Int = 0
) {
    fun forEstudiante(id: String): EstudianteDtos {
        Log.i("testForEstudiante", "id = $id | name = $name | city=$city | age=$age ")
        return EstudianteDtos(
            id = id,
            name = name,
            city = city,
            age = age
        )
    }
}