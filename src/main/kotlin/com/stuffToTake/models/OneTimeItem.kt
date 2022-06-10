package com.stuffToTake.models

class OneTimeItem(itemName: String,
                  itemAmount: String,
                  itemToTake: Boolean,
                  itemCategories: Set<Category> = emptySet())
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    /**
     * The return value is 2, so the (OneTime)Item will be deleted.
     */
    override fun markedAsToTake(): Int {  // TODO create tests
        toTake = false
        return 2
    }

    /**
     * Returns always 0.
     */
    override fun unmarkedAsToTake(): Int {  // TODO create tests
        toTake = true
        return 0
    }

    override fun toString(): String {
        return "One Time ${super.toString()}"
    }

    override fun copy(): OneTimeItem {  // TODO create tests
        return OneTimeItem(this.name, this.amount, this.toTake, this.categories)
    }

}
