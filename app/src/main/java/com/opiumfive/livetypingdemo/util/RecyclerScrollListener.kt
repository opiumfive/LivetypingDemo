package com.opiumfive.livetypingdemo.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val VISIBLE_THRESHOLD = 5

class RecyclerScrollListener(private val listener: () -> Unit) : RecyclerView.OnScrollListener() {

    private var previousTotalItemCount = 0
    private var loadingMode = true

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
            if (loadingMode) {
                if (totalItemCount > previousTotalItemCount) {
                    loadingMode = false
                    previousTotalItemCount = totalItemCount
                }
            }
            if (!loadingMode && visibleItemCount + firstVisibleItemPosition >= totalItemCount - VISIBLE_THRESHOLD) {
                loadingMode = true
                listener.invoke()
            }
        }
    }
}