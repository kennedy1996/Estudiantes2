package com.estudiantes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        searchingData()
        settingFab()
        settingAdapter()
        updateDataAfterTime()

    }

    private fun searchingData() {
        viewModel.searchEstudianteFirebase()
    }

    private fun updateDataAfterTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.getSearchEstudianteFirebase().value?.size!! > 0) {
                adapter!!.notifyDataSetChanged()
            }
        }, 3000)
    }

    private fun settingFab() {
        val fab = findViewById<FloatingActionButton>(R.id.list_estudiantes_activity_fab)
        fab.setOnClickListener {
            dialogNewEstudiante(this, viewModel, adapter)
        }
    }

    private fun settingAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.list_estudiantes_activity_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListEstudiantesAdapter(
            this,
            viewModel
        )
        recyclerView.adapter = adapter
    }
}