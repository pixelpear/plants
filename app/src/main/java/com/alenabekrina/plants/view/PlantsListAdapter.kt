package com.alenabekrina.plants.view

import android.arch.lifecycle.LiveData
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.alenabekrina.plants.App
import com.alenabekrina.plants.R
import com.alenabekrina.plants.model.Plant
import com.alenabekrina.plants.repository.Repository
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

class PlantsListAdapter(private val plantsDataset: LiveData<List<Plant>>,
                        private val glide: RequestManager,
                        private val plantsListActivity: PlantsListActivity
) :
        RecyclerView.Adapter<PlantsListAdapter.PlantViewHolder>() {
        @Inject
        lateinit var repository: Repository
        var actionMode: ActionMode? = null
        var selectedPlants = mutableMapOf<Int, Plant>()

        fun selectPlant(plant: Plant, holder: PlantViewHolder) {
            selectedPlants[plant.id] = plant
            glide.load(R.drawable.plantpic)
                .into(holder.plantPic)
        }

        fun unselectPlant(plant: Plant, holder: PlantViewHolder) {
            selectedPlants.remove(plant.id)
            val optionsForRoundPic = RequestOptions().circleCrop()
            glide.load(R.drawable.plantpic)
                .apply(optionsForRoundPic)
                .into(holder.plantPic)
        }

        fun deletePlants() {
            for (plant in selectedPlants.values) {
                repository.deletePlant(plant)
            }
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        class PlantViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
            val plantName = layout.findViewById<TextView>(R.id.textView_plant_name)
            val plantPic = layout.findViewById<ImageView>(R.id.imageView_plant_pic)
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): PlantViewHolder {
            val context = parent.context
            // inject fields by Dagger
            val app = context.applicationContext as App
            app.component.injectPlantsListAdapter(this)

            val constraintLayout = LayoutInflater.from(context)
                .inflate(R.layout.plant_list_item, parent, false) as ConstraintLayout

            return PlantViewHolder(constraintLayout)
        }

        override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
            val plant = plantsDataset.value?.get(position)!!
            holder.plantName.text = plant.name

            val optionsForRoundPic = RequestOptions().circleCrop()
            glide.load(R.drawable.plantpic)
                .apply(optionsForRoundPic)
                .into(holder.plantPic)

            holder.layout.setOnLongClickListener {
                    if (actionMode == null) {
                        actionMode = plantsListActivity.startActionMode(mActionModeCallback)
                        selectPlant(plant, holder)
                    }
                true
                }

            holder.layout.setOnClickListener {
                if (actionMode != null) {
                    if (selectedPlants[plant.id] == null) {
                        selectPlant(plant, holder)
                    } else unselectPlant(plant, holder)
                } else return@setOnClickListener
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = plantsDataset.value?.size ?: 0


    val mActionModeCallback : ActionMode.Callback = object : ActionMode.Callback {

        // Called when the action mode is created; startActionMode() was called
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.plants_list_action_mode_menu, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_delete -> {
                    deletePlants()
                    Log.i("PlantsListActivity", "Delete plant")
                    mode.finish() // Action picked, so close the CAB
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            // TODO: store selected plants, fix multiple selection
            actionMode = null
            notifyDataSetChanged()
            selectedPlants.clear()
        }
    }
}