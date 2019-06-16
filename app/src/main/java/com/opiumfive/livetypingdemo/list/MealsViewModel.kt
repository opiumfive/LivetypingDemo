package com.opiumfive.livetypingdemo.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opiumfive.livetypingdemo.data.Category
import com.opiumfive.livetypingdemo.data.Meal

class MealsViewModel(val repo: MealsRepo) : ViewModel() {

    val catalogData = MutableLiveData<List<Meal>>()
    val catsData = MutableLiveData<List<Category>>()

    var toBeLoaded = mutableListOf<Category>()
    var loaded = mutableListOf<Category>()

    fun getCats(force: Boolean = false) = repo.getCategories(force) {
        catsData.value = it
        toBeLoaded.clear()
        loaded.clear()
        toBeLoaded.addAll(it ?: emptyList())
        getNextCategory()
    }

    fun getFilters() = repo.getCategories {
        catsData.value = it
    }

    fun getByCategory(category: Category?) = repo.getByCategory(category?.strCategory) { catalogData.value = it }

    fun getNextCategory() {
        if (toBeLoaded.isNotEmpty()) {

            for (pos in 0 until toBeLoaded.size) {
                val category = toBeLoaded[pos]
                if (repo.isCategoryActive(category)) {
                    getByCategory(category)
                    loaded.add(category)
                    toBeLoaded.removeAt(pos)
                    break
                }
            }
        }
    }

    fun getCurrentList() {
        val meals = mutableListOf<Meal>()

        loaded.forEach { category ->
            if (repo.isCategoryActive(category)) {
                repo.getByCategory(category.strCategory) { it?.forEach { meals.add(it) } }
            }
        }

        if (meals.isNotEmpty()) {
            catalogData.value = meals
        } else {
            getNextCategory()
        }
    }
}