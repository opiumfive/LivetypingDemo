package com.opiumfive.livetypingdemo.util

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import android.graphics.drawable.Drawable
import com.opiumfive.livetypingdemo.R

class RecyclerLineDecorator(context: Context): RecyclerView.ItemDecoration() {

    private var divider: Drawable?

    init {
        divider = ContextCompat.getDrawable(context, R.drawable.line_divider)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}