package com.stuffToTake.models

abstract class AbstractItem(var name: String, var amount: String, var to_take: Boolean) {

    // optionally the amount can be given as an Integer instead of a String
    constructor(name: String, amount: Int, to_take: Boolean) : this(name, amount.toString(), to_take)

    var categories: MutableSet<Category> = mutableSetOf()
        protected set

    /**
     * Changes the "to_take" variable to true.
     */
    open fun markAsToTake(): Unit {
        to_take = true
    }

    /**
     * Changes the "to_take" variable to false.
     */
    open fun unmarkAsToTake(): Unit {
        to_take = false
    }

    /**
     * Returns true if "amount" has a valid value, false if it's empty.
     */
    fun hasAmount(): Boolean {
        return amount != ""
    }

    /**
     * Add a category. Returns false if the category was already in the list.
     */
    fun addCategory(category: Category): Boolean {
        if (category in categories)
            return false
        else
            categories.add(category)
        return true
    }

    /**
     * Overrides the toString method for better (debug-) output.
     */
    override fun toString(): String {

        return if (hasAmount())
            "Item: $name\n" +
            "    Amount: $amount\n" +
            "    To Take: $to_take\n" +
            "    Categories: $categories"
        else
            "Item: $name\n" +
            "    Amount: -\n" +
            "    To Take: $to_take\n" +
            "    Categories: $categories"
    }

}
