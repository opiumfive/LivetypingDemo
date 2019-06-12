package com.opiumfive.livetypingdemo.data

data class Meal(val strMeal: String?, val strMealThumb: String?)

data class Meals(val meals: List<Meal>)