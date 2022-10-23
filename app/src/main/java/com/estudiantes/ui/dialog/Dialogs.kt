package com.estudiantes.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.estudiantes.R
import com.estudiantes.firebase.entity.EstudianteDtos
import com.estudiantes.ui.adapter.ListEstudiantesAdapter
import com.estudiantes.ui.viewModel.EstudiantesViewModel

fun dialogNewEstudiante(
    context: Context,
    viewModel: EstudiantesViewModel,
    adapter: RecyclerView.Adapter<ListEstudiantesAdapter.ViewHolder>?
){
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.dialog_new_estudiante)
    dialog.show()

    val nombre: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_nombre_field)
    val edad: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_edad_field)
    val ciudad: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_ciudad_field)
    val buttonSave: Button = dialog.findViewById(R.id.dialog_new_estudiante_button_save)

    buttonSave.setOnClickListener {
        val nombreText = nombre.text.toString()
        val edadText = edad.text.toString()
        val ciudadText = ciudad.text.toString()
        var error = false

        error = checkingData(nombreText, nombre, error, edadText, edad, ciudadText, ciudad)

        if(!error){
            val estudiante = EstudianteDtos(
                id = viewModel.nextID(),
                name = nombreText,
                age = edadText.toInt(),
                city = ciudadText
            )
            viewModel.sendEstudianteToFirebase(estudiante)
            dialog.dismiss()
//            viewModel.searchEstudianteFirebase()
            adapter!!.notifyDataSetChanged()
        }
    }
}
fun dialogModifyEstudiante(
    context: Context,
    viewModel: EstudiantesViewModel,
    adapter: RecyclerView.Adapter<ListEstudiantesAdapter.ViewHolder>?,
    estudianteRecebido: EstudianteDtos,
    position: Int
){
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(true)
    dialog.setContentView(R.layout.dialog_new_estudiante)
    dialog.show()

    val nombre: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_nombre_field)
    val edad: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_edad_field)
    val ciudad: EditText = dialog.findViewById(R.id.dialog_new_estudiantes_text_ciudad_field)
    val buttonSave: Button = dialog.findViewById(R.id.dialog_new_estudiante_button_save)

    nombre.setText(estudianteRecebido.name)
    edad.setText(estudianteRecebido.age.toString())
    ciudad.setText(estudianteRecebido.city)


    buttonSave.setOnClickListener {
        val nombreText = nombre.text.toString()
        val edadText = edad.text.toString()
        val ciudadText = ciudad.text.toString()
        var error = false

        error = checkingData(nombreText, nombre, error, edadText, edad, ciudadText, ciudad)

        if(!error){
            val estudiante = EstudianteDtos(
                id = estudianteRecebido.id,
                name = nombreText,
                age = edadText.toInt(),
                city = ciudadText
            )
            viewModel.changeEstudianteToFirebase(estudiante)
            dialog.dismiss()
            adapter!!.notifyItemChanged(position)
        }
    }
}



private fun checkingData(
    nombreText: String,
    nombre: EditText,
    error: Boolean,
    edadText: String,
    edad: EditText,
    ciudadText: String,
    ciudad: EditText
): Boolean {
    var error1 = error
    if (nombreText.isNullOrBlank()) {
        nombre.error = "Nombre és invalido"
        error1 = true
    }
    else if (!nombreText.matches("[a-zA-Z\\u00C0-\\u00FF ]+".toRegex())){
        nombre.error = "Nombre tiene numero"
        error1 = true
    }
    if (edadText.isNullOrBlank()) {
        edad.error = "Edad és invalida"
        error1 = true
    }else if (edadText.toInt()<0 || edadText.toInt()>150) {
        edad.error = "Edad és incorrecta!"
        error1 = true
    }
    if (ciudadText.isNullOrBlank()) {
        ciudad.error = "Ciudad és invalido"
        error1 = true
    }else if (ciudadText.length<2){
        ciudad.error = "Ciudad és incorrecta"
        error1 = true
    }else if (!ciudadText.matches("[a-zA-Z\\u00C0-\\u00FF ]+".toRegex())){
        ciudad.error = "Ciudad tiene numero"
        error1 = true
    }

    return error1
}