package com.example.tinderlikecardstack.cardstack.view


interface CardView {

    /** Binds the data object to the view itself. */
    fun <T>bind(item: T)

    /** Indicates that the view has been recycled */
    fun onCardRecycled()

    /** Indicates when the card view is moved to the top of the card stack. */
    fun <T>onMovedToTop(item: T)

    /** Indicates when the card view is removed from the top of the card stack.  */
    fun <T>onRemovedFromTop(item: T)

    /** Called when the view is attached to the Window  */
    fun onAttachedToCardCollectionLayout(cardCollectionLayout: CardCollectionLayout)

    /** Called when the view is detached from the window  */
    fun onDetachedFromCardCollectionLayout(cardCollectionLayout: CardCollectionLayout)

}