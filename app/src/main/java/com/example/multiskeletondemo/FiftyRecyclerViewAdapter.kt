package com.example.multiskeletondemo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multiskeletondemo.fiftyshadesof.FiftyShadesOf
import kotlinx.android.synthetic.main.list_item.view.*

class FiftyRecyclerViewAdapter(
    private val shade: FiftyShadesOf
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        (holder as ForecastViewHolder).bind(shade)
    }

    override fun getItemCount(): Int {
        return 5
    }

    class ForecastViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        internal fun bind(shades: FiftyShadesOf) {
            itemView.apply {
                shades.on(skeleton_item_pic)
                shades.on(skeleton_item_description)
                shades.on(skeleton_item_title)
                shades.on(skeleton_item_value)
            }
        }

        override fun onClick(v: View?) {
        }
    }
}
