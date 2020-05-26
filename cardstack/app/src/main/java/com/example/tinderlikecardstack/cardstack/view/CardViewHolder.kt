package com.example.tinderlikecardstack.cardstack.view

import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tinderlikecardstack.cardstack.animation.SwipeAnimation
import com.example.tinderlikecardstack.cardstack.model.Card
import com.example.tinderlikecardstack.cardstack.model.Direction


class CardViewHolder<M : Card<M>>(view: View) : RecyclerView.ViewHolder(view) {
//    private val cardView: CardView<M?>?
    private var cardViewModel: M? = null
    private var isAtTop = false
    fun bind(viewModel: M?) {
        cardViewModel = viewModel
//        cardView.bind(viewModel)
    }

    fun onCardAtTop(isAtTop: Boolean) {
        if (isAtTop && !this.isAtTop) {
//            cardView.onMovedToTop(cardViewModel)
        } else if (!isAtTop && this.isAtTop) {
//            cardView.onRemovedFromTop(cardViewModel)
        }
        this.isAtTop = isAtTop
    }

    fun onCardViewRecycled() {
//        cardView.onCardViewRecycled()
    }

    fun onAttachedToCardCollectionLayout(cardCollectionLayout: CardCollectionLayout) {
//        cardView.onAttachedToCardCollectionLayout(cardCollectionLayout)
    }

    fun onDetachedFromCardCollectionLayout(
        cardCollectionLayout: CardCollectionLayout
    ) {
//        cardView.onDetachedFromCardCollectionLayout(cardCollectionLayout)
    }

    val appearingAnimation: SwipeAnimation = cardViewModel.getAppearingAnimation()

    val disappearingAnimation: SwipeAnimation?
        get() = cardViewModel.getDisappearingAnimation()

    val isSwipable: Boolean
        get() = isSwipable(cardViewModel)

    fun isSwipable(viewModel: M?): Boolean {
        return true
    }

    /**
     * When the card is is about to be dragged. [.isSwipable] is called first. If [ ][.isSwipable] returns true then the this method is called. If [.isSwipable] return
     * false then this method is not called at all.
     *
     * @return true if the card can be dragged in the given direction
     */
    fun canDragCard(direction: Direction?): Boolean {
        return canDragCard(direction, cardViewModel)
    }

    fun canDragCard(direction: Direction?, viewModel: M?): Boolean {
        return true
    }

    interface Factory<VH : CardViewHolder<*>?, T : Card<T>> {
        fun createViewHolder(parent: ViewGroup?, viewType: Int): VH?
        fun getViewType(card: T?): Int
    }

    init {
        require(view is CardView) { "cardView must implement " + CardView::class.java.simpleName }
//        cardView = view as CardView<M?>?
    }
}

private fun <M> M?.getDisappearingAnimation(): SwipeAnimation? {
    TODO("Not yet implemented")
}

private fun <M> M?.getAppearingAnimation(): SwipeAnimation {
    TODO("Not yet implemented")
}
