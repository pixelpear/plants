package me.bekrina.plantstracker.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import me.bekrina.plantstracker.R
import me.bekrina.plantstracker.dagger.DaggerAppComponent
import me.bekrina.plantstracker.model.Plant
import me.bekrina.plantstracker.viewmodel.PlantsViewModel

class PlantListActivity : AppCompatActivity() {
    private lateinit var plantsListRecyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var plantsData: LiveData<List<Plant>>

    override fun onCreate(savedInstanceState: Bundle?) {
        //DaggerAppComponent.builder()
        //    .build()
            //.injectActivity(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_list)

        viewManager = LinearLayoutManager(this)

        var viewmodel = ViewModelProviders.of(this).get(PlantsViewModel::class.java)

        plantsData = viewmodel.getPlantsDesc()
        plantsData.observe(this, object : Observer<List<Plant>> {
            override fun onChanged(events: List<Plant>?) {

                viewAdapter = PlantsListAdapter(plantsData)
                plantsListRecyclerView = findViewById<RecyclerView>(R.id.plants_list_recycler_view).apply {
                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    setHasFixedSize(true)

                    // use a linear layout manager
                    layoutManager = viewManager

                    // specify an viewAdapter (see also next example)
                    adapter = viewAdapter
                }


            }
        })




    }
}
