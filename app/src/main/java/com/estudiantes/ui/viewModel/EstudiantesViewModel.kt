package com.estudiantes.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.repository.EstudiantesRepository

class EstudiantesViewModel: ViewModel() {
    private val repository = EstudiantesRepository()
    private var listEstudiantes = MutableLiveData<List<EstudianteDtos>>()

    fun sendEstudianteToFirebase(estudiante: EstudianteDtos){
        repository.sendToFirebase(estudiante)
    }
    fun searchEstudianteFirebase(){
        listEstudiantes.value =repository.searchFirebase()
    }
    fun getSearchEstudianteFirebase(): MutableLiveData<List<EstudianteDtos>> {
        return listEstudiantes
    }
    fun nextID(): String {
        var masGrande =0
        if(!listEstudiantes.value.isNullOrEmpty()){
            for(i in listEstudiantes.value!!.indices){
                if(listEstudiantes.value!![i].id.toInt()>masGrande)
                    masGrande = listEstudiantes.value!![i].id.toInt()
            }
        }
        return (masGrande+1).toString()
    }

}