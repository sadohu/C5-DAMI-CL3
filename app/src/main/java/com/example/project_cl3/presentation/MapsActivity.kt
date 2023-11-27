package com.example.project_cl3.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.project_cl3.R
import com.example.project_cl3.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding : ActivityMapsBinding
    private lateinit var maps : GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.frgMaps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        maps = p0
        var titulo = intent.getStringExtra("titulo")!!
        var lat = intent.getDoubleExtra("lat", 0.0)
        var log = intent.getDoubleExtra("log", 0.0)
        val positionMarker = LatLng(lat, log)
        maps.addMarker(MarkerOptions().position(positionMarker).title(titulo))
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(positionMarker, 18f),4000, null)
    }
}