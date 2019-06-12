package com.opiumfive.livetypingdemo.api

import com.opiumfive.livetypingdemo.data.Categories
import com.opiumfive.livetypingdemo.data.Meals
import retrofit2.Call
import retrofit2.http.*

interface IApi {

    @GET("filter.php")
    fun getByCategory(@Query("c") category: String?): Call<Meals>

    @GET("categories.php")
    fun getCategories(): Call<Categories>
}