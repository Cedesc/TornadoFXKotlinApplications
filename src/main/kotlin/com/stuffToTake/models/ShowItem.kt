package com.stuffToTake.models

class ShowItem(val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.toTake, originalItem.categories) {

    init {
        if (originalItem is ShowItem)
            throw Exception("Cannot build a ShowedItem of a ShowedItem")
    }

    override fun markAsToTake() {
        super.markAsToTake()
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
    }

    override fun toString(): String {
        return "Showed $originalItem"
    }

    fun changeToTake() {
        toTake = toTake.not()
    }

}
