package com.estudiantes.repository

import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.firebase.service.FirebaseService

class EstudiantesRepository {
    private val firebaseService = FirebaseService()

    fun sendToFirebase(estudiante: EstudianteDtos){
        firebaseService.sendEstudianteParaFirebase(estudiante)
    }
    fun searchFirebase() : List<EstudianteDtos>{
        return firebaseService.recibeEstudianteParaFirebase()
    }
}