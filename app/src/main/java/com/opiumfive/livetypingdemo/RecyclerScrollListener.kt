package com.opiumfive.livetypingdemo

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerScrollListener(private val listener: () -> Unit) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val totalItemCount = layoutManager?.itemCount
        if (totalItemCount == layoutManager.findLastVisibleItemPosition() + 1) {
            listener.invoke()
        }
    }
}