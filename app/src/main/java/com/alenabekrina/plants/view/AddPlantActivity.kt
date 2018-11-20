package com.alenabekrina.plants.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alenabekrina.plants.R
import kotlinx.android.synthetic.main.activity_add_plant.*

class AddPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        numberpicker_watering_interval.minValue = 1
        numberpicker_watering_interval.maxValue = 30

        numberpicker_days_since_last_watering.minValue = 0
        numberpicker_days_since_last_watering.maxValue = 30
    }
}