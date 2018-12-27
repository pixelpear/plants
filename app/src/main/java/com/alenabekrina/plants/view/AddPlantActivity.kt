package com.alenabekrina.plants.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.alenabekrina.plants.App
import com.alenabekrina.plants.R
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import kotlinx.android.synthetic.main.activity_add_plant.*
import javax.inject.Inject


class AddPlantActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: PlantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)

        val app = application as App
        app.component.injectAddPlantActivity(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantsViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.add_plant_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
            R.id.action_favorite -> savePlant()
            else -> super.onOptionsItemSelected(item)
        }


    private fun savePlant(): Boolean {
        Log.i("AppPlantActivity", "saveApp")
        viewModel.createAndSavePlant(editText_name.text.toString(), editText_type.text.toString(),
            editText_watering_interval.text.toString(), editText_days_since_last_watering.text.toString())
        this.finish()
        return true
    }
}