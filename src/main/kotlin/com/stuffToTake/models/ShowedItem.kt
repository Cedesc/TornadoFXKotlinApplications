package com.stuffToTake.models

class ShowedItem(private val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.to_take) {

    init {
        if (originalItem is ShowedItem)
            throw Exception("Cannot build a ShowedItem of a ShowedItem")
    }

    override fun markAsToTake() {
        super.markAsToTake()
        TODO("Not yet implemented")
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
        TODO("Not yet implemented")
    }

}
