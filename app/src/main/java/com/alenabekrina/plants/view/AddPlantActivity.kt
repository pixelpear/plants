package com.alenabekrina.plants.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.alenabekrina.plants.R

class AddPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_add_plant)

        val nameTextView = findViewById<TextView>(R.id.textview_name)
        nameTextView.text = "sdfsd"
    }
}