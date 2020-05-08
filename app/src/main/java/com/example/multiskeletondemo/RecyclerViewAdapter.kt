package com.example.multiskeletondemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private val data: List<String?>, private val onWeatherListener: () -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var holder : RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ForecastViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ForecastViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        internal fun bind(data: String?) {
            itemView.apply {
                skeleton_item_title.text = data
            }
        }

        override fun onClick(v: View?) {
//            onWeatherListener.onWeatherClick(idCity, adapterPosition)
        }
    }
}
