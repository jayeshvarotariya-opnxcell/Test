package com.openxcell.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openxcell.data.pojo.DataEntity
import com.openxcell.databinding.InstaRowBinding

class PageAdapter :
    PagedListAdapter<DataEntity, PageAdapter.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InstaRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }

    }


    class ViewHolder(val view: InstaRowBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: DataEntity, position: Int) {
            view.data = data
            view.executePendingBindings()
        }


    }


    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<DataEntity>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: DataEntity,
                newConcert: DataEntity
            ) = oldConcert.id == newConcert.id

            override fun areContentsTheSame(
                oldConcert: DataEntity,
                newConcert: DataEntity
            ) = oldConcert.first_name == newConcert.first_name
        }
    }


}