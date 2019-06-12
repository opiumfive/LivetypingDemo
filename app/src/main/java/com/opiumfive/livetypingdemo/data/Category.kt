package com.opiumfive.livetypingdemo.data

data class Category(val strCategory: String?, var loaded: Boolean = false)

data class Categories(val categories: List<Category>?)