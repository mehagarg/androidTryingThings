package com.example.tinderlikecardstack.cardstack.cardstack

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CardStackLayoutManager : RecyclerView.LayoutManager() {
    private var isInLayout: Boolean = false

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        isInLayout = true

        detachAndScrapAttachedViews(recycler)
        if (itemCount <= 0) return

        // We assume all cards will have same size
        fillChildren(recycler, state)
    }

    private fun fillChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        TODO("Not yet implemented")
    }


    private class ChildAttachChangeListener : RecyclerView.OnChildAttachStateChangeListener {

        override fun onChildViewDetachedFromWindow(view: View) {
            TODO("Not yet implemented")
        }

        override fun onChildViewAttachedToWindow(view: View) {
            TODO("Not yet implemented")
        }

    }
}