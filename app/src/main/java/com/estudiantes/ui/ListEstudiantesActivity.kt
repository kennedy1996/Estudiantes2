package com.estudiantes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estudiantes.R
import com.estudiantes.inicializatorFirebase
import com.estudiantes.ui.adapter.ListEstudiantesAdapter
import com.estudiantes.ui.dialog.dialogNewEstudiante
import com.estudiantes.ui.viewModel.EstudiantesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListEstudiantesActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(EstudiantesViewModel::class.java)
    }

    private var adapter: RecyclerView.Adapter<ListEstudiantesAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_estudiantes_activity)
        inicializatorFirebase(this)
        viewModel.searchEstudianteFirebase()

        val fab = findViewById<FloatingActionButton>(R.id.list_estudiantes_activity_fab)

        fab.setOnClickListener {
//            dialogNewEstudiante(this, viewModel)
            adapter!!.notifyDataSetChanged()
        }

        val recyclerView: RecyclerView = findViewById(R.id.list_estudiantes_activity_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ListEstudiantesAdapter(
            this,
            viewModel
        )
        recyclerView.adapter = adapter

    }
}