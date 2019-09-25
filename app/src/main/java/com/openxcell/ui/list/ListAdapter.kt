package com.openxcell.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openxcell.data.pojo.DataEntity
import com.openxcell.databinding.InstaRowBinding

class ListAdapter(val mData: MutableList<DataEntity>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InstaRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position],position)
    }


    class ViewHolder(val view: InstaRowBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(data: DataEntity, position: Int) {
            view.data = data
            view.executePendingBindings()
        }


    }


}