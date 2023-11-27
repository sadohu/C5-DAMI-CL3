package com.example.project_cl3.domain

import com.google.firebase.firestore.GeoPoint

data class Restaurante (
    var nombre : String? = "",
    var descripcion : String? = "",
    var img : String? = "",
    var ubicacion : GeoPoint? = GeoPoint(0.0, 0.0),
)