package com.estudiantes.firebase.service

import com.estudiantes.firebase.entity.EstudianteDtos
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class FirebaseService {

    private val db = FirebaseFirestore.getInstance(Firebase.app("bd-firebase"))
    private val collection = db.collection("usuarios")

    fun sendEstudianteParaFirebase(){

        val estudiante = EstudianteDtos(
            id = "2",
            name = "Julian",
            city="Ponferrada",
            age = 26
        )
            val documento = estudiante.id?.let { id ->
                collection.document(id.toString())
            } ?: collection.document()
            documento.set(estudiante)
    }
}