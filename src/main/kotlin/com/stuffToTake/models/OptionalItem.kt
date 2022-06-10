package com.stuffToTake.models

class OptionalItem(itemName: String,
                   itemAmount: String,
                   itemToTake: Boolean,
                   itemCategories: Set<Category> = emptySet())
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    /**
     * If the return is 0, nothing happens.
     * If the return is 1, the identical (Optional)Item will be changed to "toTake".
     */
    override fun markedAsToTake(): Int {  // TODO create tests
        val oldToTake = toTake
        toTake = false
        return if (oldToTake)
            0
        else
            // set the identical Optional Item in the other list to "toTake"
            1
    }

    /**
     * Returns always 0.
     */
    override fun unmarkedAsToTake(): Int {  // TODO create tests
        toTake = false
        return 0
    }

    override fun toString(): String {
        return "Optional ${super.toString()}"
    }

    override fun copy(): OptionalItem {  // TODO create tests
        return OptionalItem(this.name, this.amount, this.toTake, this.categories)
    }

}
