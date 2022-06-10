package com.stuffToTake.models

class OptionalItem(itemName: String,
                   itemAmount: String,
                   itemToTake: Boolean,
                   itemCategories: Set<Category> = emptySet())
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    override fun markedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests
        if (! showItemToTake)
            // set the identical Optional Item in the other list to "toTake"
            TODO("Not yet implemented")
        toTake = false
        return toTake
    }

    override fun unmarkedAsToTake(showItemToTake: Boolean): Boolean {  // TODO create tests
        return false
    }

    override fun toString(): String {
        return "Optional ${super.toString()}"
    }

    override fun copy(): OptionalItem {  // TODO create tests
        return OptionalItem(this.name, this.amount, this.toTake, this.categories)
    }

}
