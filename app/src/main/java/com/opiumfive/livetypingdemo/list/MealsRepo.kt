package com.opiumfive.livetypingdemo.list

import com.iterika.marvel.api.enqueue
import com.opiumfive.livetypingdemo.api.IApi
import com.opiumfive.livetypingdemo.data.Category
import com.opiumfive.livetypingdemo.data.Meal

class MealsRepo(private val api: IApi) {

    // cache
    val currentCats = mutableListOf<Category>()
    val byCatsMap = mutableMapOf<String, List<Meal>?>()

    fun isCategoryActive(cat: Category) = currentCats.findLast { cat.strCategory == it.strCategory }?.active == true

    fun getCategories(force: Boolean = false, result: (List<Category>?) -> Unit) {
        if (force) {
            currentCats.clear()
        }

        if (currentCats.isNotEmpty()) {
            result.invoke(currentCats)
            return
        }

        api.getCategories().enqueue(
            {
                val cats = it.body()?.categories
                cats?.forEach { currentCats.add(it.apply { active = true }) }
                result.invoke(cats)
            }, {
                result.invoke(emptyList())
            }
        )
    }

    fun getByCategory(category: String?, result: (List<Meal>?) -> Unit) {
        if (byCatsMap.containsKey(category)) {
            result.invoke(byCatsMap.get(category))
            return
        }

        api.getByCategory(category).enqueue(
            {
                val meals = it.body()?.meals
                meals?.forEach { it.category = category  }
                category?.let{ byCatsMap.put(it, meals) }
                result.invoke(meals)
            }, {
                result.invoke(emptyList())
            }
        )
    }
}