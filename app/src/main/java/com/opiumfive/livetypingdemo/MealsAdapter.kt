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
        mealList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_meal, parent, false))

    private fun getItem(position: Int) = mealList[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun getItemCount() = mealList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Meal?) {
            GlideApp.with(itemView.context).load(cat?.strMealThumb).placeholder(R.drawable.placeholder).into(itemView.image)
            itemView.text.text = cat?.strMeal
        }
    }
}