package com.alenabekrina.plants.view

import android.arch.lifecycle.LiveData
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.alenabekrina.plants.R
import com.alenabekrina.plants.model.Plant

class PlantsListAdapter(private val plantsDataset: LiveData<List<Plant>>) :
        RecyclerView.Adapter<PlantsListAdapter.PlantViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        class PlantViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
            val plantName = layout.findViewById<TextView>(R.id.textView_plant_name)
        }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): PlantViewHolder {
            // create a new view
            val constraintLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_list_item, parent, false) as ConstraintLayout
            // set the view's size, margins, paddings and layout parameters
            //...
            return PlantViewHolder(constraintLayout)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.plantName.text = plantsDataset.value?.get(position)?.name ?: ""
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = plantsDataset.value?.size ?: 0
    }