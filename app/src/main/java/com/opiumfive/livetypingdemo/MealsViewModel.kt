package com.opiumfive.livetypingdemo

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

    fun getByCategory(category: Category?) = repo.getByCategory(category?.strCategory) { catalogData.value = it }

    fun getNextCategory() {
        if (toBeLoaded.isNotEmpty()) {
            getByCategory(toBeLoaded[0])
            loaded.add(toBeLoaded[0])
            toBeLoaded.removeAt(0)
        }
    }
}