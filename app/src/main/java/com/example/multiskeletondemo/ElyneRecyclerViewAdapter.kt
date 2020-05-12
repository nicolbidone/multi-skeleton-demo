package com.example.multiskeletondemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.elyne_list_item.view.*

class ElyneRecyclerViewAdapter(private val data: MutableList<String?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.elyne_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal fun bind(data: String?) {
            itemView.apply {
                elyne_skeleton_item_title.text = data
            }
        }

        override fun onClick(v: View?) {
        }
    }
}
