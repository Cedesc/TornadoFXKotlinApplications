package com.stuffToTake.models

abstract class AbstractItem(var name: String, var amount: String, var to_take: Boolean) {

    private var categories: MutableList<Category> = mutableListOf()

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
        TODO("Not yet implemented")
        // return super.toString()
    }

}
