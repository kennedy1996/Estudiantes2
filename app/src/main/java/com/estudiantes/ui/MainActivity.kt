package com.estudiantes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.estudiantes.R
import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.firebase.service.FirebaseService
import com.estudiantes.inicializatorFirebase
import com.estudiantes.ui.viewModel.EstudiantesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(EstudiantesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializatorFirebase(this)

        viewModel.sendEstudianteToFirebase(
            EstudianteDtos(
                "03", "Juan", "", 9
            )
        )

    }
}