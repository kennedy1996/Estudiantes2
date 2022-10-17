package com.estudiantes.ui.viewModel

import androidx.lifecycle.ViewModel
import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.repository.EstudiantesRepository

class EstudiantesViewModel: ViewModel() {
    private val repository = EstudiantesRepository()

    fun sendEstudianteToFirebase(estudiante: EstudianteDtos){
        repository.sendToFirebase(estudiante)
    }


}