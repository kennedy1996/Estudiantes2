package com.estudiantes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.estudiantes.R
import com.estudiantes.inicializatorFirebase
import com.estudiantes.ui.dialog.dialogNewEstudiante
import com.estudiantes.ui.viewModel.EstudiantesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListEstudiantesActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(EstudiantesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_estudiantes_activity)
        inicializatorFirebase(this)

        val fab = findViewById<FloatingActionButton>(R.id.list_estudiantes_activity_fab)

        fab.setOnClickListener {
//            viewModel.sendEstudianteToFirebase(
//                EstudianteDtos(
//                    "5", "Isabel", "León", 80
//                )
//            )
            dialogNewEstudiante(this, viewModel)
        }

    }
}