package com.stuffToTake.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleSetProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableSet
import tornadofx.*

abstract class AbstractItem(itemName: String,
                            itemAmount: String,
                            itemToTake: Boolean,
                            itemCategories: Set<Category> = emptySet())  {

    // the amount can be given as an Integer instead of a String
    constructor(itemName: String, itemAmount: Int, itemToTake: Boolean, itemCategories: Set<Category> = emptySet())
            : this(itemName, itemAmount.toString(), itemToTake, itemCategories)


    val nameProperty: SimpleStringProperty = SimpleStringProperty(itemName)
    var name: String by nameProperty

    val amountProperty: SimpleStringProperty = SimpleStringProperty(itemAmount)
    var amount: String by amountProperty

    val toTakeProperty: SimpleBooleanProperty = SimpleBooleanProperty(itemToTake)
    var toTake: Boolean by toTakeProperty

    val categoriesProperty: SimpleSetProperty<Category> =
        SimpleSetProperty(mutableSetOf<Category>().toObservable())
    val categories: ObservableSet<Category> by categoriesProperty


    init {
        addMultipleCategories(itemCategories)
    }

    /**
     * If the return is 0, nothing happens.
     * If the return is 1, the identical (Optional)Item will be changed to "toTake".
     * If the return is 2, the (OneTime)Item will be deleted.
     * If the return is something else, an Exception will be given.
     *
     *
     * EssentialItem:
     *  true, 0
     *
     * OptionalItem:
     *  original true -> false, 0
     *  original false -> false, 1
     *
     * EssentialItem:
     *  false, 2
     */
    abstract fun markedAsToTake(): Int

    /**
     * If the return is 0, nothing happens.
     * If the return is 1, the identical (Optional)Item will be changed to "toTake".
     * If the return is 2, the (OneTime)Item will be deleted.
     * If the return is something else, an Exception will be given.
     *
     *
     * EssentialItem:
     *  true, 0
     *
     * OptionalItem:
     *  false, 0
     *
     * EssentialItem:
     *  true, 0
     */
    abstract fun unmarkedAsToTake(): Int

    /**
     * Returns true if "amount" has a valid value, false if it's empty.
     */
    fun hasAmount(): Boolean {
        return amount != ""
    }

    /**
     * Adds a category. Returns false if the category was already in the list.
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
            "    Categories: $categories\n" +
            "    To Take: $toTake"
        else
            "Item: $name\n" +
            "    Amount: -\n" +
            "    Categories: $categories\n" +
            "    To Take: $toTake"
    }

    /**
     * Overrides the equals method and checks if the toStrings of both are equal.
     */
    override fun equals(other: Any?): Boolean {
        if (other is AbstractItem) {
            return toString() == other.toString()
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = nameProperty.hashCode()
        result = 31 * result + amountProperty.hashCode()
        result = 31 * result + toTakeProperty.hashCode()
        result = 31 * result + categoriesProperty.hashCode()
        return result
    }

    abstract fun copy(): AbstractItem

    /**
     * Adds multiple categories.
     */
    protected fun addMultipleCategories(inputCategories: Set<Category>) {
        inputCategories.forEach { category ->
            if (! this.addCategory(category))
                throw Exception("Duplicated Category? " +
                        "This shouldn't be possible, because it is a set what is given, not a list")
        }
    }

}
