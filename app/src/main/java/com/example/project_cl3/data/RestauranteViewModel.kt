package com.example.project_cl3.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_cl3.domain.Restaurante
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.lang.Exception

class RestauranteViewModel: ViewModel(){
    private lateinit var firestore : FirebaseFirestore
    private val _getList = MutableLiveData<List<Restaurante>>()
    val getList : LiveData<List<Restaurante>> = _getList

    private fun getApiList() : MutableList<Restaurante>{
        firestore = FirebaseFirestore.getInstance()
        val listRestaurant : MutableList<Restaurante> = ArrayList()

        firestore.collection("Restaurante").get().addOnSuccessListener { list ->
            for(item in list){
                val nombre = item.getString("nombre")
                val descripcion = item.getString("descripcion")
                val img = item.getString("img")
                val ubicacion = item.getGeoPoint("ubicacion")
                var restaurante = Restaurante(nombre, descripcion, img, ubicacion)
                listRestaurant.add(restaurante)
            }
            _getList.postValue(listRestaurant)
        }
        return listRestaurant
    }

    fun getAll() = viewModelScope.launch {
        try {
            getApiList()
        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}