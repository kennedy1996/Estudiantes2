package com.estudiantes

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

private const val TAG = "FirebaseIncializator"

fun inicializatorFirebase(context: Context) {
    val options = FirebaseOptions.Builder()
        .setProjectId("estudiantes-6a579")
        .setApplicationId("1:57048218739:android:910c5aeed54c46fb0c3e06")
        .build()
    try {
        Firebase.initialize(context, options, "bd-firebase")
    } catch (e: Exception) {
        Log.e(TAG, "FirebaseIncializator: ", e)
    }
}