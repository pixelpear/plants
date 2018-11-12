package com.alenabekrina.plants.view

import android.arch.lifecycle.LiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.alenabekrina.plants.R
import com.alenabekrina.plants.model.Plant

class PlantsListAdapter(private val myDataset: LiveData<List<Plant>>) :
        RecyclerView.Adapter<PlantsListAdapter.MyViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): MyViewHolder {
            // create a new view
            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_list_item, parent, false) as TextView
            // set the view's size, margins, paddings and layout parameters
            //...
            return MyViewHolder(textView)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.text = myDataset.value?.get(position)?.name ?: ""
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.value?.size ?: 0
    }