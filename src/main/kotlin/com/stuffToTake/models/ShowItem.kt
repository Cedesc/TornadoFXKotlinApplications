package com.stuffToTake.models

class ShowItem(val originalItem: AbstractItem)
    : AbstractItem(originalItem.name, originalItem.amount, originalItem.toTake, originalItem.categories) {

    init {
        if (originalItem is ShowItem)
            throw Exception("Cannot build a ShowedItem of a ShowedItem")
    }

    fun checked(): Int {  // TODO create tests
        return if (toTake)
            markedAsToTake()
        else
            unmarkedAsToTake()
    }

    /**
     * If the return is 0, nothing happens.
     * If the return is 1, the identical (Optional)Item will be changed to "toTake".
     * If the return is 2, the (OneTime)Item will be deleted.
     * If the return is something else, an Exception will be given.
     */
    override fun markedAsToTake(): Int {  // TODO create tests or obsolete, write it in checked() and throw Error if called?
        val action: Int = originalItem.markedAsToTake()  // TODO more efficient in the order of lines
        toTake = originalItem.toTake
        return action
    }

    /**
     * If the return is 0, nothing happens.
     * If the return is 1, the identical (Optional)Item will be changed to "toTake".
     * If the return is 2, the (OneTime)Item will be deleted.
     * If the return is something else, an Exception will be given.
     */
    override fun unmarkedAsToTake(): Int {  // TODO create tests or obsolete, write it in checked() and throw Error if called?
        val action: Int = originalItem.markedAsToTake()  // TODO more efficient in the order of lines
        toTake = originalItem.toTake
        return action
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
