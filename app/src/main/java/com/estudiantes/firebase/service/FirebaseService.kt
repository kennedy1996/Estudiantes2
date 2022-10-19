package com.estudiantes.firebase.service

import com.estudiantes.firebase.converter.DocumentConverter
import com.estudiantes.firebase.entity.EstudianteDtos
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class FirebaseService {

    private val db = FirebaseFirestore.getInstance(Firebase.app("bd-firebase"))
    private val collection = db.collection("usuarios")

    fun sendEstudianteParaFirebase(estudiante: EstudianteDtos) {
            val documento = estudiante.id?.let { id ->
                collection.document(id.toString())
            } ?: collection.document()
            documento.set(estudiante)
    }

    fun recibeEstudianteParaFirebase(): List<EstudianteDtos> {
        val list = mutableListOf<EstudianteDtos>()
        collection
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let { snapshot ->
                    val estudiantes: List<EstudianteDtos> = snapshot.documents
                        .mapNotNull { documento ->
                            convertToEstudiante(documento)
                        }
                    estudiantes.forEach { estudiante ->
                        val estudiante = EstudianteDtos(
                            estudiante.id,
                            estudiante.name,
                            estudiante.city,
                            estudiante.age
                        )
                        list.add(estudiante)
                    }
                }
            }
        return list
    }

    private fun convertToEstudiante(documento: DocumentSnapshot): EstudianteDtos? =
        documento.toObject(DocumentConverter::class.java)?.forEstudiante(documento.id)
}