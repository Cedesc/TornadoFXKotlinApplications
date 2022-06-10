package com.stuffToTake.models

class ShowItem(val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.toTake, originalItem.categories) {

    init {
        if (originalItem is ShowItem)
            throw Exception("Cannot build a ShowedItem of a ShowedItem")
    }

    fun checked() {  // TODO create tests
        if (toTake)
            markedAsToTake(true)
        else
            unmarkedAsToTake(false)
    }

    override fun markedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests or obsolete, write it in checked() and throw Error if called?
        toTake = originalItem.markedAsToTake(toTake)
        return toTake
    }

    override fun unmarkedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests or obsolete, write it in checked() and throw Error if called?
        toTake = originalItem.markedAsToTake(toTake)
        return toTake
    }

    override fun toString(): String {
        return "Showed $originalItem"
    }

    fun changeToTake() {
        toTake = toTake.not()
    }

    override fun copy(): ShowItem {  // TODO create tests
        val copiedItem = ShowItem(originalItem.copy())
        // It isn't enough to copy the original item,
        // since the Show Item attributes may differ from the original item attributes.
        copiedItem.name = this.name
        copiedItem.amount = this.amount
        copiedItem.toTake = this.toTake
        copiedItem.categories.clear()
        copiedItem.addMultipleCategories(this.categories)
        return copiedItem
    }

}
