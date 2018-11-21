package com.alenabekrina.plants.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alenabekrina.plants.R

class AddPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}