package com.stuffToTake.models

class OptionalItem(itemName: String,
                   itemAmount: String,
                   itemToTake: Boolean,
                   itemCategories: Set<Category>)
    : AbstractItem(itemName, itemAmount, itemToTake, itemCategories) {

    // the categories don't have to be given
    constructor(name: String, amount: String, toTake: Boolean)
            : this(name, amount, toTake, emptySet())
    // the amount can be given as an Integer instead of a String
    constructor(name: String, amount: Int, toTake: Boolean, categories: Set<Category>)
            : this(name, amount.toString(), toTake, categories)
    constructor(name: String, amount: Int, toTake: Boolean)
            : this(name, amount.toString(), toTake, emptySet())


    override fun markAsToTake() {
        super.markAsToTake()
        TODO("Not yet implemented")
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Optional ${super.toString()}"
    }

}
