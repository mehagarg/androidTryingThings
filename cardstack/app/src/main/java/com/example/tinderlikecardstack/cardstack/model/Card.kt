package com.example.tinderlikecardstack.cardstack.model

import com.example.tinderlikecardstack.cardstack.animation.SwipeAnimation

class Card<T>(val id: String, val item: T) {

    /**
     * This values is set when the Card is reverting/rewinding to its original position. When using
     * the values from the ViewModel we should make sure that we reset the value once we have consumed
     * it.
     */
    var showRevertIndicator = false

    var appearingAnimation: SwipeAnimation? = null

    var disappearingAnimation: SwipeAnimation? = null

}