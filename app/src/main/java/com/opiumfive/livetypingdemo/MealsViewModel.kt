package com.opiumfive.livetypingdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opiumfive.livetypingdemo.data.Category
import com.opiumfive.livetypingdemo.data.Meal

class MealsViewModel(val repo: MealsRepo) : ViewModel() {

    val catalogData = MutableLiveData<List<Meal>>()

    var toBeLoaded = mutableListOf<Category>()

    fun getCats(force: Boolean = false) = repo.getCategories(force) {
        toBeLoaded.clear()
        toBeLoaded.addAll(it ?: emptyList())
        getNextCategory()
    }

    fun getByCategory(category: Category?) = repo.getByCategory(category?.strCategory) { catalogData.value = it }

    fun getNextCategory() {
        if (toBeLoaded.isNotEmpty()) {
            getByCategory(toBeLoaded[0])
            toBeLoaded.removeAt(0)
        }
    }
}