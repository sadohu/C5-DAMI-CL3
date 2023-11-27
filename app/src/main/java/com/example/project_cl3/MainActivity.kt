package com.example.project_cl3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_cl3.data.RestauranteViewModel
import com.example.project_cl3.databinding.ActivityMainBinding
import com.example.project_cl3.domain.Restaurante
import com.example.project_cl3.presentation.MapsActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint

class MainActivity : AppCompatActivity(), MainAdapter.ICard {
    private lateinit var binding : ActivityMainBinding

    private lateinit var firestore : FirebaseFirestore
    private var listRestaurant : MutableList<Restaurante> = ArrayList()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var viewModel: RestauranteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this)[RestauranteViewModel::class.java]
        firestore = FirebaseFirestore.getInstance()
        mainAdapter = MainAdapter(listRestaurant, this)
        binding.rvRestaurante.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvRestaurante.adapter = mainAdapter
    }

    private fun initObservers(){
        viewModel.getList.observe(this){
            mainAdapter.update(it)
        }
        viewModel.getAll()
    }

    override fun onCardClick(item: Restaurante) {
        startActivity(Intent(this, MapsActivity::class.java).apply {
            putExtra("titulo", item.nombre)
            putExtra("lat", item.ubicacion?.latitude)
            putExtra("log", item.ubicacion?.longitude)
        })
    }
}