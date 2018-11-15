package com.alenabekrina.plants.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alenabekrina.plants.App
import com.alenabekrina.plants.R
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.viewmodel.PlantsViewModel
import javax.inject.Inject

class PlantsListActivity : AppCompatActivity() {
    private lateinit var plantsListRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var plantsData: LiveData<List<Plant>>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_list)


        val app = application as App
        app.component.injectActivity(this)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlantsViewModel::class.java)

        plantsData = viewModel.getPlantsDesc()
        plantsData.observe(this, Observer<List<Plant>> {
            viewAdapter = PlantsListAdapter(plantsData)
            plantsListRecyclerView = findViewById<RecyclerView>(R.id.plants_list_recycler_view).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter

                // use a linear layout manager
                layoutManager = viewManager
            }
        })

        viewManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, AddPlantActivity::class.java)
            startActivity(intent)
        }


    }
}
