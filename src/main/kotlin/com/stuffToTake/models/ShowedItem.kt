package com.stuffToTake.models

class ShowedItem(val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.to_take) {

    init {
        if (originalItem is ShowedItem)
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

}
