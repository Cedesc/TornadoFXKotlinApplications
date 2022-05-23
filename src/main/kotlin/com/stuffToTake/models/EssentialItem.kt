package com.stuffToTake.models

class EssentialItem(itemName: String,
                    itemAmount: String,
                    itemToTake: Boolean,
                    itemCategories: Set<Category> = emptySet())
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    override fun markAsToTake() {
        super.markAsToTake()
        TODO("Not yet implemented")
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Essential ${super.toString()}"
    }

    override fun copy(): EssentialItem {  // TODO create tests
        return EssentialItem(this.name, this.amount, this.toTake, this.categories)
    }

}
