package com.opiumfive.livetypingdemo.data

data class Category(val strCategory: String?, var active: Boolean = true)

data class Categories(val categories: List<Category>?)