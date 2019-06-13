package com.opiumfive.livetypingdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opiumfive.livetypingdemo.data.Meal
import kotlinx.android.synthetic.main.view_meal.view.*

class MealsAdapter:  RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    var mealList = mutableListOf<Meal>()

    fun clear() {
        mealList.clear()
        notifyDataSetChanged()
    }

    fun addList(list: List<Meal>) {
        val startPosition = mealList.size
        mealList.addAll(list)
        notifyItemRangeInserted(startPosition, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_meal, parent, false))

    private fun getItem(position: Int) = mealList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val showHeader = position == 0 || getItem(position).category != getItem(position - 1).category
        holder.bind(getItem(position), showHeader)
    }

    override fun getItemCount() = mealList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Meal?, showHeader: Boolean) {
            GlideApp.with(itemView.context).load(cat?.strMealThumb).placeholder(R.drawable.placeholder).into(itemView.image)
            itemView.text.text = cat?.strMeal
            itemView.category.text = cat?.category
            itemView.category.visibility = if (showHeader) View.VISIBLE else View.GONE
        }
    }
}