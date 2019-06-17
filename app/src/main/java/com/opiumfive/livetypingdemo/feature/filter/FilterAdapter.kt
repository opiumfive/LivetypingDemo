package com.opiumfive.livetypingdemo.feature.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opiumfive.livetypingdemo.R
import com.opiumfive.livetypingdemo.data.Category
import kotlinx.android.synthetic.main.view_filter.view.*

class FilterAdapter:  RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var filterList: List<Category> = emptyList()

    fun addList(list: List<Category>) {
        filterList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_filter, parent, false))

    private fun getItem(position: Int) = filterList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), position)

    override fun getItemCount() = filterList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Category?, pos: Int) {
            itemView.text.text = cat?.strCategory
            itemView.image.visibility = if (cat?.active == true) View.VISIBLE else View.INVISIBLE

            itemView.setOnClickListener {
                cat?.active = cat?.active?.not() ?: true
                notifyItemChanged(pos)
            }
        }
    }
}